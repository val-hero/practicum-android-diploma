package ru.practicum.android.diploma.favorites.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.AppDatabase
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.search.domain.models.Vacancy

class FavoritesRepositoryImpl (private val database: AppDatabase) : FavoritesRepository {

    override suspend fun addVacancy(vacancy: Vacancy) {
        database
            .favoritesVacanciesDao()
            .addVacancy(toFavoriteVacanciesEntity(vacancy))
    }

    override suspend fun deleteVacancy(id: Long) {
        database
            .favoritesVacanciesDao()
            .deleteVacancy(id)
    }

    override fun getFavorites(): Flow<List<Vacancy>> = flow {
        val vacancies = database
            .favoritesVacanciesDao()
            .getFavoritesVacancies()
        emit(convertFromFavoritesVacanciesEntity(vacancies))

    }

    override fun isFavorite(id: Long): Flow<Boolean> = flow {
        val isInFavorite = database
            .favoritesVacanciesDao()
            .isFavorite(id)
        emit(isInFavorite)
    }

    private fun convertFromFavoritesVacanciesEntity(vacancies: List<FavoritesVacanciesEntity>): List<Vacancy> {
        return vacancies.map { vacancy -> toVacancy(vacancy) }
    }

}