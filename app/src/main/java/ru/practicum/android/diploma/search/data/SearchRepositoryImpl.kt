package ru.practicum.android.diploma.search.data

import ru.practicum.android.diploma.core.AppDatabase
import ru.practicum.android.diploma.search.data.network.RetrofitApi
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.models.Vacancy

class SearchRepositoryImpl(
    private val networkClient: RetrofitApi,
    private val database: AppDatabase
): SearchRepository {
    override suspend fun getVacancy(id: String): Vacancy {
        TODO("Not yet implemented")
    }

    override suspend fun getSimilarVacancies(title: String): List<Vacancy> {
        TODO("Not yet implemented")
    }

    override suspend fun getVacanciesWithFilter(filters: Map<String, String>): List<Vacancy> {
        TODO("Not yet implemented")
    }
}