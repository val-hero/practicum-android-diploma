package ru.practicum.android.diploma.similar_vacancies.ui.state

import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.search.domain.models.Vacancy

sealed interface SimilarVacanciesScreenState {
    data object Loading : SimilarVacanciesScreenState
    data class Content(val vacancies: List<Vacancy>) : SimilarVacanciesScreenState
    data class Error(val type: ErrorType) : SimilarVacanciesScreenState
}