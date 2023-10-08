package ru.practicum.android.diploma.favorites.ui.state

import ru.practicum.android.diploma.search.domain.models.Vacancy

sealed interface FavoritesScreenState {
    object Empty : FavoritesScreenState

    data class FavoritesVacancies(
        val vacancies: List<Vacancy>,
    ) : FavoritesScreenState
}