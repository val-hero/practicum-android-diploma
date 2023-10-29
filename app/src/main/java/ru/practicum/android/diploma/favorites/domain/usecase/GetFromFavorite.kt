package ru.practicum.android.diploma.favorites.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.search.domain.models.VacancyDetails

class GetFromFavorite (private val repo: FavoritesRepository) {

    suspend operator fun invoke(id: String) : Flow<VacancyDetails> {
        return repo.getVacancyFromDb(id)
    }
}