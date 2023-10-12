package ru.practicum.android.diploma.vacancy_details.ui.state

import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.search.domain.models.VacancyDetails

sealed interface VacancyDetailsScreenState {
    data object Loading : VacancyDetailsScreenState

    data class Content(val vacancyDetails: VacancyDetails) : VacancyDetailsScreenState

    data class Error(val type: ErrorType) : VacancyDetailsScreenState
}