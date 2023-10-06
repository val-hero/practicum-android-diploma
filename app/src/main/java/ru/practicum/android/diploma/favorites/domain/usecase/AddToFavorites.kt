package ru.practicum.android.diploma.favorites.domain.usecase

import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.search.domain.models.Vacancy

class AddToFavorites(private val repo: FavoritesRepository) {

    suspend operator fun invoke(vacancy: Vacancy) {
        repo.addVacancy(vacancy)
    }
}