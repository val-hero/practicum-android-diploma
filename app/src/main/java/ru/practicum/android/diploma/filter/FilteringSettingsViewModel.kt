package ru.practicum.android.diploma.filter

import android.util.Log
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
    private val saveAreaUseCase: SaveAreaUseCase,
    private val saveCountryUseCase: SaveCountryUseCase,
    private val saveIndustryUseCase: SaveIndustryUseCase
) : ViewModel() {

    private val _filterSettings = MutableLiveData<FilterParameters?>()
    fun updateFilterSettings(): LiveData<FilterParameters?> = _filterSettings

    fun getFilterSettings() {
        Log.i("FILTERUODATE", "Запущен метод getFilterSettings")
        viewModelScope.launch {
            _filterSettings.postValue(getFilterSettingsUseCase())
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

    fun clearAreaField() {
        viewModelScope.launch {
            saveAreaUseCase(null)
            saveCountryUseCase(null)
        }
    }

    fun clearIndustryField() {
        viewModelScope.launch{
            saveIndustryUseCase(null)
        }
    }

    fun saveSalaryFlag(isChecked: Boolean?) {
        Log.i("FILTERUODATE", "Отправляю в настройки фильтра $isChecked")
        viewModelScope.launch {
            saveSalaryFlagUseCase(isChecked)
        }
    }

}