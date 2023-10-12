package ru.practicum.android.diploma.favorites.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.data.entity.FavoriteVacancyEntity
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository

class GetFromFavorite (private val repo: FavoritesRepository) {

    suspend operator fun invoke(id: String) : Flow<FavoriteVacancyEntity> {
        return repo.getVacancyFromDb(id)
    }

}