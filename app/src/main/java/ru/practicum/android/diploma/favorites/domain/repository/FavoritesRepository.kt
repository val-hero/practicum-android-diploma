package ru.practicum.android.diploma.favorites.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.models.Vacancy

interface FavoritesRepository {

    fun getFavorites(): Flow<List<Vacancy>>

    suspend fun addVacancy(vacancy: Vacancy)

    suspend fun deleteVacancy(id: Long)

    fun isFavorite(id: Long): Flow<Boolean>

}