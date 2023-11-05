package ru.practicum.android.diploma.filter.ui.selectRegion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.usecase.GetAreasInCountryUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetAreasUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveAreaUseCase
import ru.practicum.android.diploma.search.domain.models.fields.Area

class SelectRegionViewModel(
    private val getAreasUseCase: GetAreasUseCase,
    private val getAreasInCountryUseCase: GetAreasInCountryUseCase,
    private val saveAreaUseCase: SaveAreaUseCase,
) : ViewModel() {

    private val _areas = MutableLiveData<Resource<List<Area>>>()
    val areas: LiveData<Resource<List<Area>>> = _areas

    fun getAreas(countryId: String?) {
        viewModelScope.launch {
            if (countryId.isNullOrBlank()) {
                getAreasUseCase().collect() {
                    _areas.value = it
                }
            } else {
                getAreasInCountryUseCase(countryId).collect() {
                    _areas.value = it
                }
            }
        }
    }

    fun saveArea(area: Area) {
        viewModelScope.launch {
            saveAreaUseCase(area)
        }
    }
}