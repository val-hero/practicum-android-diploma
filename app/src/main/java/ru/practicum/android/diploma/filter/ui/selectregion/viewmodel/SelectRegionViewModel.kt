package ru.practicum.android.diploma.filter.ui.selectregion.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.filter.domain.usecase.GetAreasUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetFilterSettingsUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveAreaUseCase
import ru.practicum.android.diploma.search.domain.models.fields.Area

class SelectRegionViewModel(
    private val getAreasUseCase: GetAreasUseCase,
    private val saveAreaUseCase: SaveAreaUseCase,
    private val getFilterSettingsUseCase: GetFilterSettingsUseCase
) : ViewModel() {

    private val _areas = MutableLiveData<Resource<List<Area>>>()
    val areas: LiveData<Resource<List<Area>>> = _areas
    private val _countrySettings = MutableLiveData<Country?>()
    val countrySettings: LiveData<Country?> = _countrySettings

    fun getAreas() {
        viewModelScope.launch {
            getAreasUseCase().collect() {
                _areas.value = it
            }
        }
    }

    fun saveArea(area: Area) {
        viewModelScope.launch {
            saveAreaUseCase(area)
        }
    }

    fun getCountry() {
        viewModelScope.launch {
            _countrySettings.postValue(getFilterSettingsUseCase()?.country)
        }
    }
}