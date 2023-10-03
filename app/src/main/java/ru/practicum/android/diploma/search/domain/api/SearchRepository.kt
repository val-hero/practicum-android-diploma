package ru.practicum.android.diploma.search.domain.api

import ru.practicum.android.diploma.search.domain.models.Vacancy

interface SearchRepository {
    suspend fun getVacancy(id: String): Vacancy

    suspend fun getSimilarVacancies(title: String): List<Vacancy>

    suspend fun getVacanciesWithFilter(filters: Map<String, String>): List<Vacancy>

}