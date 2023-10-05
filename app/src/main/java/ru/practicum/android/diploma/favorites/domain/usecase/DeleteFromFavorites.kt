package ru.practicum.android.diploma.favorites.domain.usecase

import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository

class DeleteFromFavorites(private val repo: FavoritesRepository) {

    suspend fun deleteVacancy(id: Long) {
        repo.deleteVacancy(id)
    }
}