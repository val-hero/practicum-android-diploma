package ru.practicum.android.diploma.favorites.domain.usecase

import kotlinx.coroutines.flow.Flow

class IsInFavoriteCheck(private val repo: FavoritesRepository) {

    fun checkFavorites(id:Long) : Flow<Boolean> {
        return repo.isFavorite(id)
    }
}