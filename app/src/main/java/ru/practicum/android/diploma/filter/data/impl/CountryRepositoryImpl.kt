package ru.practicum.android.diploma.filter.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.CountryRepository
import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.search.data.network.RetrofitApi


class CountryRepositoryImpl(private val api :RetrofitApi):CountryRepository {
    override suspend fun getCountries(): Flow<Resource<List<Country>>> = flow {
        try {
            val countries = api.getCountries().map { it.toCountry() }
            emit(Resource.Success(countries))
        } catch (e: Exception) {

        }
    }
}


