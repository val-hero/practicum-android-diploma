package ru.practicum.android.diploma.search.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.core.utils.ResourcesFlow
import ru.practicum.android.diploma.search.data.network.api.RetrofitApi
import ru.practicum.android.diploma.search.data.network.dto.toVacancy
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.models.Vacancy

class SearchRepositoryImpl(
    private val retrofitApi: RetrofitApi,
    private val context: Context
) : SearchRepository {
    override suspend fun getVacancy(id: String): Flow<Resource<Vacancy>> = flow {

        if (!isConnected()) {
            emit(Resource.Error(ErrorType.NO_CONNECTION))
            return@flow
        }

        val response = retrofitApi.getVacancy(id = id)
        when (response.resultCode) {

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

    override suspend fun getVacancies(text: String): ResourcesFlow<Vacancy> = flow {

        if (!isConnected()) {
            emit(Resource.Error(ErrorType.NO_CONNECTION))
            return@flow
        }

        val response = retrofitApi.getVacancies(text = text)
        when (response.resultCode) {

            200 -> {
                with(response) {
                    val data = result.vacancies.map {
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

    override suspend fun getSimilarVacancies(id: String): ResourcesFlow<Vacancy> = flow {

        if (!isConnected()) {
            emit(Resource.Error(ErrorType.NO_CONNECTION))
            return@flow
        }

        val response = retrofitApi.getSimilarVacancies(id = id)

        when (response.resultCode) {
            200 -> {
                with(response) {
                    val data = result.vacancies.map {
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

    override suspend fun getVacanciesWithFilter(filters: Map<String, String>): ResourcesFlow<Vacancy> =
        flow {

            if (!isConnected()) {
                emit(Resource.Error(ErrorType.NO_CONNECTION))
                return@flow
            }

            val response = retrofitApi.getVacanciesWithFilter(filters = filters)
            when (response.resultCode) {
                200 -> {
                    with(response) {
                        val data = result.vacancies.map {
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