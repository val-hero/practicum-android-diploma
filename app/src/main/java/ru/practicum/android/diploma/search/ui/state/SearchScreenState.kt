package ru.practicum.android.diploma.search.ui.state

import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.search.domain.models.Vacancy

sealed interface SearchScreenState {

    object Loading : SearchScreenState

    object Default : SearchScreenState

    data class NothingFound (
        val vacancies: List<Vacancy>)
        : SearchScreenState

    data class Success (
    val vacancies: List<Vacancy>,
    ) : SearchScreenState

    data class Error(
        val type: ErrorType
    ) : SearchScreenState

    object NoInternet : SearchScreenState

}