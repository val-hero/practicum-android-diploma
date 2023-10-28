package ru.practicum.android.diploma.similarVacancies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Constants
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.core.utils.debounce
import ru.practicum.android.diploma.search.domain.usecase.GetSimilarVacanciesUseCase
import ru.practicum.android.diploma.similarVacancies.ui.state.SimilarVacanciesScreenState

class SimilarVacanciesViewModel(
    private val getSimilarVacanciesUseCase: GetSimilarVacanciesUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<SimilarVacanciesScreenState>()
    val uiState: LiveData<SimilarVacanciesScreenState> = _uiState

    var isClickable = true
    private val vacancyClickDebounce =
        debounce<Boolean>(Constants.CLICK_DEBOUNCE_DELAY_MILLIS, viewModelScope, false) {
            isClickable = it
        }

    fun fetchSimilarVacancies(id: String) {
        _uiState.value = SimilarVacanciesScreenState.Loading

        viewModelScope.launch {
            getSimilarVacanciesUseCase(id).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _uiState.postValue(SimilarVacanciesScreenState.Content(response.data))
                    }

                    is Resource.Error -> {
                        _uiState.postValue(SimilarVacanciesScreenState.Error(response.errorType))
                    }
                }
            }
        }
    }

    fun onVacancyClick() {
        isClickable = false
        vacancyClickDebounce(true)
    }
}