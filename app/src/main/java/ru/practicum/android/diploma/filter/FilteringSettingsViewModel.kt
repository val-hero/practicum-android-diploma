package ru.practicum.android.diploma.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.filter.domain.usecase.ClearFilterSettingsUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetFilterSettingsUseCase
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
) : ViewModel() {

    private val _filterSettings = MutableLiveData<FilterParameters?>()
    fun getFilterSettings(): LiveData<FilterParameters?> = _filterSettings

    fun updateFilterSettings() {
        viewModelScope.launch {
            getFilterSettingsUseCase()?.let {
                _filterSettings.postValue(getFilterSettingsUseCase())
            }
        }
    }

    fun saveSalarySettings(salary: String?, isChecked: Boolean?) {
        viewModelScope.launch {
            saveSalaryUseCase(salary?.toInt())
            saveSalaryFlagUseCase(isChecked)
        }
    }

    fun clearFilterSettings() {
        viewModelScope.launch {
            clearFilterSettingsUseCase()
            _filterSettings.postValue(null)
        }
    }

}