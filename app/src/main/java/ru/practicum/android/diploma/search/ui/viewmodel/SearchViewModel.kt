package ru.practicum.android.diploma.search.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Constants
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.core.utils.debounce
import ru.practicum.android.diploma.search.domain.usecase.SearchUseCase
import ru.practicum.android.diploma.search.ui.state.SearchScreenState

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    val uiState = MutableLiveData<SearchScreenState>()
    fun observeUiState() : LiveData<SearchScreenState> = uiState
    var isClickable = true

    private val vacanciesSearchDebounce =
        debounce<String>(Constants.SEARCH_DEBOUNCE_DELAY_MILLIS, viewModelScope, true) { query ->
            search(query)
        }

    private val vacancyClickDebounce =
        debounce<Boolean>(Constants.CLICK_DEBOUNCE_DELAY_MILLIS, viewModelScope, false) {
            isClickable = it
        }

    fun searchDebounce(query: String) {
        if (query.isNotEmpty()) {
            vacanciesSearchDebounce(query)
        }
    }

    fun onVacancyClick() {
        isClickable = false
        vacancyClickDebounce(true)
    }

    fun search(query: String) {
        if(query.isNullOrBlank())
            return

        viewModelScope.launch {
            searchUseCase(query).collect {
                when (it) {
                    is Resource.Success -> uiState.postValue(SearchScreenState.Success(it.data))
                    else -> {}
                }
            }
        }
    }
}