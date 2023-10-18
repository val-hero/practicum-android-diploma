package ru.practicum.android.diploma.search.ui.state

import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.search.domain.models.Vacancy

sealed interface SearchScreenState {

    object Loading : SearchScreenState

    object LoadNextPage :SearchScreenState

    object Default : SearchScreenState

    data class NothingFound(
        val vacancies: List<Vacancy>,
        val found: Int
    ) : SearchScreenState

    data class Success(
        val vacancies: List<Vacancy>,
        val found: Int
    ) : SearchScreenState

    data class Error(
        val type: ErrorType
    ) : SearchScreenState

}