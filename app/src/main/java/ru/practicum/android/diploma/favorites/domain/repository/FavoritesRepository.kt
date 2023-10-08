package ru.practicum.android.diploma.favorites.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.models.VacancyDetails

interface FavoritesRepository {

    fun getFavorites(): Flow<List<VacancyDetails>>

    suspend fun addVacancy(vacancy: VacancyDetails)

    suspend fun deleteVacancy(id: Long)

    fun isFavorite(id: Long): Flow<Boolean>

}