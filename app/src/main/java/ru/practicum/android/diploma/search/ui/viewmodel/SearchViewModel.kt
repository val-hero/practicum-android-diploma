package ru.practicum.android.diploma.search.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Constants
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.core.utils.debounce
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.filter.domain.usecase.GetFilterSettingsUseCase
import ru.practicum.android.diploma.search.domain.usecase.SearchUseCase
import ru.practicum.android.diploma.search.domain.usecase.SearchWithFiltersUseCase
import ru.practicum.android.diploma.search.ui.state.SearchScreenState

class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val searchWithFiltersUseCase: SearchWithFiltersUseCase,
    private val filterSettingsUseCase: GetFilterSettingsUseCase,
) : ViewModel() {

    val uiState = MutableLiveData<SearchScreenState>()
    var isClickable = true
    var cancelDebounce = false
    val _filterSettingsState = MutableLiveData<Boolean>()
    val filterSettingsState: LiveData<Boolean> = _filterSettingsState
    private var filterSettings: FilterParameters? = null

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

        if (filterSettings != null) {
            searchWithFilter(getFilterSettingsAsMap(query))
        } else {
            viewModelScope.launch {
                searchUseCase(query).collect {
                    when (it) {
                        is Resource.Success -> renderState(SearchScreenState.Success(it.data))
                        is Resource.Error -> renderState(SearchScreenState.Error(it.errorType))
                    }
                }
            }
        }
    }

    fun updateFilterSettings() {
        viewModelScope.launch {
            filterSettings = filterSettingsUseCase()
            _filterSettingsState.value = filterSettings != null
        }
    }

    private fun getFilterSettingsAsMap(query: String): HashMap<String, String> {
            val result = HashMap<String, String>()
            result["text"] = query
            filterSettings?.industry?.id?.let {
                result["industry"] = filterSettings?.industry?.id as String
            }
            filterSettings?.country?.id?.let {
                result["country"] = filterSettings?.country?.id as String
            }
            filterSettings?.area?.id?.let {
                result["area"] = filterSettings?.area?.id as String
            }
            filterSettings?.salary?.let {
                result["salary"] = filterSettings?.salary.toString()
            }
            filterSettings?.onlyWithSalary?.let {
                result["only_with_salary"] = filterSettings?.onlyWithSalary.toString()
            }
            return result
    }

    fun searchWithFilter(filter: HashMap<String, String>) {
        viewModelScope.launch {
            searchWithFiltersUseCase(filter).collect {
                when(it) {
                    is Resource.Success -> renderState(SearchScreenState.Success(it.data))
                    is Resource.Error -> renderState(SearchScreenState.Error(it.errorType))
                }
            }
        }
    }

    private fun renderState(state: SearchScreenState) {
        uiState.postValue(state)
    }
}