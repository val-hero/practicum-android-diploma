package ru.practicum.android.diploma.search.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.AppDatabase
import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.core.utils.ResourcesFlow
import ru.practicum.android.diploma.search.data.network.FilteredVacanciesResponse
import ru.practicum.android.diploma.search.data.network.RetrofitApi
import ru.practicum.android.diploma.search.data.network.VacancyResponse
import ru.practicum.android.diploma.search.data.network.dto.toVacancy
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.models.Vacancy

class SearchRepositoryImpl(
    private val networkClient: RetrofitApi,
    private val database: AppDatabase
): SearchRepository {
    override suspend fun getVacancy(id: String): Flow<Resource<Vacancy>> = flow {
        val response = networkClient.getVacancy(id = id)
        when(response.resultCode) {
            -1 -> {
                emit(Resource.Error(ErrorType.NO_CONNECTION))
            }

            200 -> {
                with(response) {
                    val data = result.toVacancy()
                    emit(Resource.Success(data))
                }
            }
            else -> {
                emit(Resource.Error(ErrorType.NOT_FOUND))
            }

        }

    }

    override suspend fun getSimilarVacancies(id: String): ResourcesFlow<Vacancy> = flow {
        val response = networkClient.getSimilarVacancies(id = id)
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error(ErrorType.NO_CONNECTION))
            }

            200 -> {
                with(response) {
                    val data = result.map {
                        it.toVacancy()
                    }
                    emit(Resource.Success(data))
                }
            }

            else -> {
                emit(Resource.Error(ErrorType.NOT_FOUND))
            }
        }
    }

    override suspend fun getVacanciesWithFilter(filters: Map<String, String>): ResourcesFlow<Vacancy> = flow {
        val response = networkClient.getVacanciesWithFilter(filters = filters)
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error(ErrorType.NO_CONNECTION))
            }

            200 -> {
                with(response) {
                    val data = result.map {
                        it.toVacancy()
                    }
                    emit(Resource.Success(data))
                }
            }

            else -> {
                emit(Resource.Error(ErrorType.NOT_FOUND))
            }
        }
    }
}