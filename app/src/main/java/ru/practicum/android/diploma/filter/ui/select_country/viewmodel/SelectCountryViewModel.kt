package ru.practicum.android.diploma.filter.ui.select_country.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.filter.domain.usecase.GetCountriesUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveAreaUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveCountryUseCase

class SelectCountryViewModel(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val saveCountryUseCase: SaveCountryUseCase,
    private val saveAreaUseCase: SaveAreaUseCase
) : ViewModel() {

    private val _countries = MutableLiveData<Resource<List<Country>>>()
    val countries: LiveData<Resource<List<Country>>> = _countries

    fun saveCountry(country: Country) {
        viewModelScope.launch {
            saveCountryUseCase(country)
            saveAreaUseCase(null)
        }
    }
    fun getCountries() {
        viewModelScope.launch {
            getCountriesUseCase().collect() {
                _countries.value = it
            }
        }
    }
}