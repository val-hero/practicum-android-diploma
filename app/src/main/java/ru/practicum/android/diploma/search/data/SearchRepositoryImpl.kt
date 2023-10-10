package ru.practicum.android.diploma.search.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.core.utils.ResourcesFlow
import ru.practicum.android.diploma.search.data.network.VacanciesResponse
import ru.practicum.android.diploma.search.data.network.VacancyDetailsResponse
import ru.practicum.android.diploma.search.data.network.api.ApiRequest
import ru.practicum.android.diploma.search.data.network.client.NetworkClient
import ru.practicum.android.diploma.search.data.network.dto.toDomain
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.search.domain.models.VacancyDetails

class SearchRepositoryImpl(
    private val networkClient: NetworkClient
) : SearchRepository {
    override suspend fun getVacancy(id: String): Flow<Resource<VacancyDetails>> = flow {

        val apiResponse =
            networkClient.doRequest(ApiRequest.VacancyDetailsRequest(id)) as VacancyDetailsResponse

        if (apiResponse.resultCode == 200)
            emit(Resource.Success(apiResponse.vacancyDetails.toDomain()))
        else
            emit(Resource.Error(getErrorType(apiResponse.resultCode)))
    }

    override suspend fun getVacancies(query: String): ResourcesFlow<Vacancy> = flow {
        val perPage = 100
        var page = 0
        var totalPages = 0
        var allVacancies: List<Vacancy> = emptyList()

        do {
            val apiResponse = networkClient.doRequest(
                ApiRequest.VacancySearchRequest(
                    query,
                    page,
                    perPage
                )
            ) as VacanciesResponse

            if (apiResponse.resultCode == 200) {
                allVacancies += apiResponse.vacancies.map { it.toDomain() }
            } else {
                emit(Resource.Error(getErrorType(apiResponse.resultCode)))
                return@flow
            }

            page++
            totalPages = apiResponse.pages

        } while (page < totalPages)

        emit(Resource.Success(allVacancies))
    }


    override suspend fun getSimilarVacancies(id: String): ResourcesFlow<Vacancy> = flow {

        val apiResponse =
            networkClient.doRequest(ApiRequest.SimilarVacancySearchRequest(id)) as VacanciesResponse

        if (apiResponse.resultCode == 200)
            emit(Resource.Success(apiResponse.vacancies.map { it.toDomain() }))
        else
            emit(Resource.Error(getErrorType(apiResponse.resultCode)))

    }

    override suspend fun getVacanciesWithFilter(filters: Map<String, String>): ResourcesFlow<Vacancy> =
        flow {
            val apiResponse =
                networkClient.doRequest(ApiRequest.VacancySearchWithFiltersRequest(filters)) as VacanciesResponse

            if (apiResponse.resultCode == 200)
                emit(Resource.Success(apiResponse.vacancies.map { it.toDomain() }))
            else
                emit(Resource.Error(getErrorType(apiResponse.resultCode)))
        }


    private fun getErrorType(code: Int): ErrorType = when (code) {
        -1 -> ErrorType.NO_CONNECTION
        400 -> ErrorType.BAD_REQUEST
        403 -> ErrorType.CAPTCHA_REQUIRED
        404 -> ErrorType.NOT_FOUND
        else -> ErrorType.UNEXPECTED
    }
}