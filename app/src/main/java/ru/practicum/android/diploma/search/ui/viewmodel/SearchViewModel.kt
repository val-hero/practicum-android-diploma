package ru.practicum.android.diploma.search.ui.viewmodel

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
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    val uiState = MutableLiveData<SearchScreenState>()
    var isClickable = true
    var cancelDebounce = false

    private val vacanciesSearchDebounce =
        debounce<String>(Constants.SEARCH_DEBOUNCE_DELAY_MILLIS, viewModelScope, true) { query ->
            search(query)
        }

    private val vacancyClickDebounce =
        debounce<Boolean>(Constants.CLICK_DEBOUNCE_DELAY_MILLIS, viewModelScope, false) {
            isClickable = it
        }

    fun searchDebounce(query: String) {
        if (query.isNotEmpty() && !cancelDebounce) {
            vacanciesSearchDebounce(query)
        }
    }

    fun onVacancyClick() {
        isClickable = false
        vacancyClickDebounce(true)
    }

    fun search(query: String) {
        if (query.isNullOrBlank())
            return

        renderState(SearchScreenState.Loading)

        viewModelScope.launch {

            searchUseCase(query).collect {
                when (it) {
                    //TODO vacancies count
                    is Resource.Success -> renderState(SearchScreenState.Success(it.data))
                    else -> {}
                }
            }
        }
    }



    private fun renderState(state: SearchScreenState) {
        uiState.postValue(state)
    }
}