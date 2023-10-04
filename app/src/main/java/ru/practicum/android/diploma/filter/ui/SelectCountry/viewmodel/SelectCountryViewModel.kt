package ru.practicum.android.diploma.filter.ui.SelectCountry.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.filter.domain.usecase.GetCountriesUseCase

class SelectCountryViewModel(private val getCountriesUseCase: GetCountriesUseCase) : ViewModel() {

    private val _countries = MutableLiveData<Resource<List<Country>>>()
    val countries: LiveData<Resource<List<Country>>> = _countries

    fun getCountries() {
        viewModelScope.launch {
            getCountriesUseCase().collect() {
                _countries.value = it
            }
        }
    }
}