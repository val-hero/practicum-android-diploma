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
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.search.domain.usecase.SearchUseCase
import ru.practicum.android.diploma.search.ui.state.SearchScreenState

class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val filterSettingsUseCase: GetFilterSettingsUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<SearchScreenState>()
    val uiState: LiveData<SearchScreenState> = _uiState
    var isClickable = true
    private val _filterSettingsState = MutableLiveData<Boolean>()
    val filterSettingsState: LiveData<Boolean> = _filterSettingsState
    private var filterSettings: FilterParameters? = null
    private var currentPage = 0
    private var maxPages = Int.MAX_VALUE
    private var latestSearchQuery: String? = null
    var isScrollable = true

    var vacanciesList: MutableList<Vacancy> = mutableListOf()

    private val vacanciesSearchDebounce =
        debounce<String?>(Constants.SEARCH_DEBOUNCE_DELAY_MILLIS, viewModelScope, true) { query ->
            search(query)
        }

    private val vacancyClickDebounce =
        debounce<Boolean>(Constants.CLICK_DEBOUNCE_DELAY_MILLIS, viewModelScope, false) {
            isClickable = it
        }

    private val scrollDebounce =
        debounce<Boolean>(Constants.SCROLL_DEBOUNCE_DELAY_MILLIS, viewModelScope, false) {
            isScrollable = it
        }

    fun onVacancyClick() {
        isClickable = false
        vacancyClickDebounce(true)
    }

    fun onLastVacancyScroll() {
        isScrollable = false
        scrollDebounce(true)
        loadNextPage()
    }

    fun search(query: String?) {
        if (isInvalidQuery(query)) return

        if (_uiState.value !is SearchScreenState.LoadNextPage)
            renderState(SearchScreenState.Loading)

        latestSearchQuery = query

        val titleQuery = "NAME:$query"

        viewModelScope.launch {
            searchUseCase(titleQuery, currentPage, getFilterSettingsAsMap()).collect {
                when (it) {
                    is Resource.Success -> {
                        renderState(SearchScreenState.Success(it.data.vacancies, it.data.found))
                        currentPage = it.data.page
                        maxPages = it.data.pages
                        vacanciesList.addAll(it.data.vacancies)
                    }

                    is Resource.Error -> renderState(SearchScreenState.Error(it.errorType))
                }
            }
        }
    }

    private fun loadNextPage() {
        if (currentPage >= maxPages || maxPages == 1)
            return

        renderState(SearchScreenState.LoadNextPage)
        vacanciesSearchDebounce(latestSearchQuery)
        currentPage++
    }

    fun updateFilterSettings() {
        viewModelScope.launch {
            filterSettings = filterSettingsUseCase()
            _filterSettingsState.value = filterSettings != null
        }
    }

    fun onSearchQueryChanged(query: String?) {
        if (query.isNullOrBlank() || query != latestSearchQuery)
            vacanciesList.clear()

        vacanciesSearchDebounce(query)
    }

    private fun getFilterSettingsAsMap(): HashMap<String, String> {
        val result = HashMap<String, String>()

        filterSettings?.country?.id?.let {
            result["area"] = filterSettings?.country?.id as String
        }

        filterSettings?.area?.id?.let {
            result["area"] = filterSettings?.area?.id as String
        }

        filterSettings?.industry?.id?.let {
            result["industry"] = filterSettings?.industry?.id as String
        }

        filterSettings?.salary?.let {
            result["salary"] = filterSettings?.salary.toString()
        }
        filterSettings?.onlyWithSalary?.let {
            result["only_with_salary"] = filterSettings?.onlyWithSalary.toString()
        }
        return result
    }

    private fun renderState(state: SearchScreenState) {
        _uiState.postValue(state)
    }

    private fun isInvalidQuery(query: String?): Boolean {
        return (query.isNullOrBlank() || query == latestSearchQuery) && _uiState.value !is SearchScreenState.LoadNextPage
    }
}