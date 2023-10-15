package ru.practicum.android.diploma.favorites.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.AppDatabase
import ru.practicum.android.diploma.favorites.data.entity.FavoriteVacancyEntity
import ru.practicum.android.diploma.favorites.data.entity.toDomain
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.search.domain.models.VacancyDetails
import ru.practicum.android.diploma.search.domain.models.toFavoriteEntity


class FavoritesRepositoryImpl(
    private val database: AppDatabase,
) : FavoritesRepository {

    override suspend fun addVacancy(vacancy: VacancyDetails) {
        database
            .favoritesVacanciesDao()
            .addVacancy(vacancy.toFavoriteEntity())
    }

    override suspend fun deleteVacancy(id: String) {
        database
            .favoritesVacanciesDao()
            .deleteVacancy(id)
    }

    override fun getFavorites(): Flow<List<VacancyDetails>> = flow {
        val vacancies = database
            .favoritesVacanciesDao()
            .getFavoritesVacancies()
        emit(vacancies.map { it.toDomain() })

    }

    override fun isFavorite(id: String): Flow<Boolean> = flow {
        val isInFavorite = database
            .favoritesVacanciesDao()
            .isFavorite(id)
        emit(isInFavorite)
    }

    override suspend fun getVacancyFromDb(id: String): Flow<VacancyDetails> = flow {
        val vacancyDetails = database
            .favoritesVacanciesDao()
            .getFavoriteVacancyById(id)
        emit(vacancyDetails.toDomain())
    }
}