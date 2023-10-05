package ru.practicum.android.diploma.favorites.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository

class IsInFavoriteCheck(private val repo: FavoritesRepository) {

    fun checkFavorites(id:Long) : Flow<Boolean> {
        return repo.isFavorite(id)
    }
}