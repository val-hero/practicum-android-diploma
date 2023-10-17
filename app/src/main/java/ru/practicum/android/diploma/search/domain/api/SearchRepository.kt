package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.core.utils.ResourcesFlow
import ru.practicum.android.diploma.search.domain.models.SearchResponse
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.search.domain.models.VacancyDetails

interface SearchRepository {
    suspend fun getVacancy(id: String): Flow<Resource<VacancyDetails>>

    suspend fun getVacancies(
        query: String,
        page: Int,
        perPage: Int,
        pages: Int
    ): Flow<Resource<SearchResponse>>

    suspend fun getSimilarVacancies(id: String): ResourcesFlow<Vacancy>

    suspend fun getVacanciesWithFilter(filters: Map<String, String>): ResourcesFlow<Vacancy>


}