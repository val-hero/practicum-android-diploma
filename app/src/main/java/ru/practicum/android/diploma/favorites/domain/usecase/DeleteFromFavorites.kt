package ru.practicum.android.diploma.favorites.domain.usecase

class DeleteFromFavorites(private val repo: FavoritesRepository) {

    suspend fun deleteVacancy(id: Long) {
        repo.deleteVacancy(id)
    }
}