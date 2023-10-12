package ru.practicum.android.diploma.favorites.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.data.entity.FavoriteVacancyEntity
import ru.practicum.android.diploma.search.domain.models.VacancyDetails

interface FavoritesRepository {

    fun getFavorites(): Flow<List<VacancyDetails>>

    suspend fun addVacancy(vacancy: VacancyDetails)

    suspend fun deleteVacancy(id: String)

    fun isFavorite(id: String): Flow<Boolean>

    suspend fun getVacancyFromDb(id: String) : Flow<FavoriteVacancyEntity>

}