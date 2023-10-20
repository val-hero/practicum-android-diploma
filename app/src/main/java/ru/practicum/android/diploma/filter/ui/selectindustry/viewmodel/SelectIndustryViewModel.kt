package ru.practicum.android.diploma.filter.ui.selectindustry.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.models.fields.Industry
import ru.practicum.android.diploma.filter.domain.usecase.GetIndustriesUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveIndustryUseCase

class SelectIndustryViewModel(
    private val getIndustriesUseCase: GetIndustriesUseCase,
    private val saveIndustryUseCase: SaveIndustryUseCase
) : ViewModel() {
    private val _industry = MutableLiveData<Resource<List<Industry>>>()
    val industry: LiveData<Resource<List<Industry>>> = _industry

    fun getIndustry() {
        viewModelScope.launch {
            getIndustriesUseCase().collect() {
                _industry.value = it
            }
        }
    }

    fun saveIndustry(industry: Industry) {
        viewModelScope.launch {
            saveIndustryUseCase(industry)
        }
    }


}