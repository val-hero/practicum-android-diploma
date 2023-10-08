package ru.practicum.android.diploma.favorites.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.search.domain.models.VacancyDetails

class GetFavorites(private val repo: FavoritesRepository) {

    operator fun invoke() : Flow<List<VacancyDetails>> {
        return repo.getFavorites()
    }
}