package ru.practicum.android.diploma.favorites.domain.usecase

import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.search.domain.models.Vacancy

class AddToFavorite(private val repo: FavoritesRepository) {

    suspend fun addVacancy(vacancy: Vacancy) {
        repo.addVacancy(vacancy)
    }
}