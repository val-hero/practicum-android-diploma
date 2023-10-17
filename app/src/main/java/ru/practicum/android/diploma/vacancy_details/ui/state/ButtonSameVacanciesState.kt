package ru.practicum.android.diploma.vacancy_details.ui.state

sealed interface ButtonSameVacanciesState {

    object Active : ButtonSameVacanciesState

    object Inactive : ButtonSameVacanciesState
}