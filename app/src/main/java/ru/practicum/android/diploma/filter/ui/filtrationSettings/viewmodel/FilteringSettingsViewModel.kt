package ru.practicum.android.diploma.filter.ui.filtrationSettings.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.filter.domain.usecase.ClearFilterSettingsUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetCountryByIdUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetFilterSettingsUseCase
import ru.practicum.android.diploma.filter.domain.usecase.RestoreFilterSettingsUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveAreaUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveCountryUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveIndustryUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveSalaryFlagUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveSalaryUseCase

class FilteringSettingsViewModel(
    private val getFilterSettingsUseCase: GetFilterSettingsUseCase,
    private val clearFilterSettingsUseCase: ClearFilterSettingsUseCase,
    private val saveSalaryFlagUseCase: SaveSalaryFlagUseCase,
    private val saveSalaryUseCase: SaveSalaryUseCase,
    private val saveAreaUseCase: SaveAreaUseCase,
    private val saveCountryUseCase: SaveCountryUseCase,
    private val saveIndustryUseCase: SaveIndustryUseCase,
    private val restoreFilterSettingsUseCase: RestoreFilterSettingsUseCase,
    private val getCountryByIdUseCase: GetCountryByIdUseCase
) : ViewModel() {

    private val _filterSettings = MutableLiveData<FilterParameters?>()
    val filterSettings: LiveData<FilterParameters?> = _filterSettings
    private var previousFilterParameters: FilterParameters? = null
    private var isPreviousSettingsSaved = false
    private var savedCountry: Country? = null
    private var countrySetFlag = false

    fun getFilterSettings() {
        viewModelScope.launch {
            val currentSettings = getFilterSettingsUseCase()
            savedCountry = currentSettings?.country
            if (savedCountry != null) {
                countrySetFlag = false
            }
            _filterSettings.postValue(currentSettings)
            if(!isPreviousSettingsSaved) {
                previousFilterParameters = currentSettings
                isPreviousSettingsSaved = true
            }
        }
    }

    fun saveSalary(salary: String?) {
        viewModelScope.launch {
            if (!salary.isNullOrBlank())
                saveSalaryUseCase(salary.toInt())
            else
                saveSalaryUseCase(null)
        }
    }

    fun clearFilterSettings() {
        viewModelScope.launch {
            clearFilterSettingsUseCase()
            _filterSettings.postValue(null)
        }
    }

    fun clearAreaField() {
        viewModelScope.launch {
            saveAreaUseCase(null)
            saveCountryUseCase(null)
        }
    }

    fun clearIndustryField() {
        viewModelScope.launch {
            saveIndustryUseCase(null)
        }
    }

    fun saveSalaryFlag(isChecked: Boolean?) {
        viewModelScope.launch {
            saveSalaryFlagUseCase(isChecked)
        }
    }

    fun restoreFilterSettings() {
        viewModelScope.launch {
            restoreFilterSettingsUseCase(previousFilterParameters)
        }
    }

    fun setCountryById(countryId: String) {
        if(!countrySetFlag) {
            countrySetFlag = true
            viewModelScope.launch {
                val currentCountry = getCountryByIdUseCase(countryId)
                saveCountryUseCase(currentCountry)
            }
        }
    }
}