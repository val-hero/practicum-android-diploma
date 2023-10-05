package ru.practicum.android.diploma.favorites.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.models.Vacancy

class GetFavorites(private val repo: FavoritesRepository) {

    fun getFavorites() : Flow<List<Vacancy>> {
        return repo.getFavorites()
    }
}