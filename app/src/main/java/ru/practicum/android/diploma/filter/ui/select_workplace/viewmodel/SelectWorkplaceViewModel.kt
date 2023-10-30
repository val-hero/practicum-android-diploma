package ru.practicum.android.diploma.filter.ui.select_workplace.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.filter.domain.usecase.GetFilterSettingsUseCase
import ru.practicum.android.diploma.filter.domain.usecase.RestoreFilterSettingsUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveAreaUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveCountryUseCase

class SelectWorkplaceViewModel(
    private val getFilterSettingsUseCase: GetFilterSettingsUseCase,
    private val saveCountryUseCase: SaveCountryUseCase,
    private val saveAreaUseCase: SaveAreaUseCase,
    private val restoreFilterSettingsUseCase: RestoreFilterSettingsUseCase
) : ViewModel() {

    private val _filterSettings = MutableLiveData<FilterParameters?>()
    private var previousFilterParameters: FilterParameters? = null
    private var isPreviosParametersSaved = false
    val filterSettings: LiveData<FilterParameters?> = _filterSettings

    fun getFilterSettings() {
        viewModelScope.launch {
            val currentParameters = getFilterSettingsUseCase()
            _filterSettings.postValue(currentParameters)
            if(!isPreviosParametersSaved) {
                previousFilterParameters = currentParameters
                isPreviosParametersSaved = true
            }
        }
    }

    fun clearCountryField() {
        viewModelScope.launch {
            saveCountryUseCase(null)
        }
    }

    fun clearAreaField() {
        viewModelScope.launch {
            saveAreaUseCase(null)
        }
    }

    fun restoreFilterSettings() {
        viewModelScope.launch {
            restoreFilterSettingsUseCase(previousFilterParameters)
        }
    }
}