package ru.practicum.android.diploma.vacancy_details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.search.domain.usecase.GetVacancyDetailsUseCase

class VacancyDetailsViewModel(
    private val getVacancyDetailsUseCase: GetVacancyDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<VacancyDetailsScreenState>()
    val uiState: LiveData<VacancyDetailsScreenState> = _uiState

    fun fetchDetails(id: String) {
        _uiState.value = VacancyDetailsScreenState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            getVacancyDetailsUseCase(id).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _uiState.postValue(VacancyDetailsScreenState.Content(response.data))
                    }

                    is Resource.Error -> {
                        _uiState.postValue(VacancyDetailsScreenState.Error(response.errorType))
                    }
                }
            }
        }
    }
}