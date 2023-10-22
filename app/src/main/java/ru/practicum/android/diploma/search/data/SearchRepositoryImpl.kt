package ru.practicum.android.diploma.search.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.core.utils.ResourcesFlow
import ru.practicum.android.diploma.search.data.network.SearchResponseDto
import ru.practicum.android.diploma.search.data.network.api.ApiRequest
import ru.practicum.android.diploma.search.data.network.client.NetworkClient
import ru.practicum.android.diploma.search.data.network.dto.VacancyDetailsDto
import ru.practicum.android.diploma.search.data.network.dto.toDomain
import ru.practicum.android.diploma.search.data.network.toDomain
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.models.SearchResponse
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.search.domain.models.VacancyDetails

class SearchRepositoryImpl(
    private val networkClient: NetworkClient
) : SearchRepository {
    override suspend fun getVacancy(id: String): Flow<Resource<VacancyDetails>> = flow {

        val apiResponse =
            networkClient.doRequest(ApiRequest.VacancyDetailsRequest(id))

        if (apiResponse is VacancyDetailsDto && apiResponse.resultCode == 200) {
            emit(Resource.Success(apiResponse.toDomain()))
        } else
            emit(Resource.Error(getErrorType(apiResponse.resultCode)))
    }

    override suspend fun getVacancies(
        query: String,
        page: Int,
        filters: HashMap<String, String>
    ): Flow<Resource<SearchResponse>> = flow {

        val apiResponse =
            networkClient.doRequest(
                ApiRequest.VacancySearchRequest(
                    query,
                    page,
                    filters
                )
            )

        if (apiResponse is SearchResponseDto && apiResponse.resultCode == 200) {
            emit(Resource.Success(apiResponse.toDomain()))
        } else
            emit(Resource.Error(getErrorType(apiResponse.resultCode)))
    }


    override suspend fun getSimilarVacancies(id: String): ResourcesFlow<Vacancy> = flow {

        val apiResponse =
            networkClient.doRequest(ApiRequest.SimilarVacancySearchRequest(id))

        if (apiResponse is SearchResponseDto && apiResponse.resultCode == 200)
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