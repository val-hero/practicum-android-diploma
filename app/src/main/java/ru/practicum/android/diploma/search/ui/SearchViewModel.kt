package ru.practicum.android.diploma.search.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.search.domain.usecase.SearchUseCase
import ru.practicum.android.diploma.search.ui.state.SearchScreenState

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    val uiState = MutableLiveData<SearchScreenState>()

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