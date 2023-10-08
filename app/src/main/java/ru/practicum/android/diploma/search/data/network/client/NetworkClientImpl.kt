package ru.practicum.android.diploma.search.data.network.client

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import retrofit2.HttpException
import ru.practicum.android.diploma.search.data.network.api.ApiRequest
import ru.practicum.android.diploma.search.data.network.api.ApiRequest.SimilarVacancySearchRequest
import ru.practicum.android.diploma.search.data.network.api.ApiRequest.VacancyDetailsRequest
import ru.practicum.android.diploma.search.data.network.api.ApiRequest.VacancySearchRequest
import ru.practicum.android.diploma.search.data.network.api.ApiRequest.VacancySearchWithFiltersRequest
import ru.practicum.android.diploma.search.data.network.api.ApiResponse
import ru.practicum.android.diploma.search.data.network.api.HeadHunterApiService

class NetworkClientImpl(
    private val context: Context,
    private val api: HeadHunterApiService
) : NetworkClient {

    override suspend fun doRequest(request: ApiRequest): ApiResponse {

        if (!isConnected()) {
            return ApiResponse().apply { resultCode = -1 }
        }

        var response = ApiResponse()
        return try {
            when (request) {
                is VacancySearchRequest -> {
                    response = api.getVacancies(request.query)
                }

                is SimilarVacancySearchRequest -> {
                    response = api.getSimilarVacancies(request.id)
                }

                is VacancyDetailsRequest -> {
                    response = api.getVacancy(request.id)
                }

                is VacancySearchWithFiltersRequest -> {
                    response = api.getVacanciesWithFilters(request.filters)
                }
            }
            response.apply { resultCode = 200 }

        } catch (exception: HttpException) {
            response.apply { resultCode = exception.code() }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> return true
            }
        }
        return false
    }

}