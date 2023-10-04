package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.core.utils.ResourcesFlow
import ru.practicum.android.diploma.search.domain.models.Vacancy

interface SearchRepository {
    suspend fun getVacancy(id: String): Flow<Resource<Vacancy>>

    suspend fun getVacancies(text: String): ResourcesFlow<Vacancy>

    suspend fun getSimilarVacancies(id: String): ResourcesFlow<Vacancy>

    suspend fun getVacanciesWithFilter(filters: Map<String, String>): ResourcesFlow<Vacancy>

}