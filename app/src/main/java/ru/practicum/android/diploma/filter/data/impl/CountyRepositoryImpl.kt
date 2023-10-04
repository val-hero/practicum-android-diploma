package ru.practicum.android.diploma.filter.data.impl

import ru.practicum.android.diploma.filter.data.network.CountryResponse
import ru.practicum.android.diploma.filter.domain.CountryRepository
import ru.practicum.android.diploma.search.data.network.RetrofitApi

class CountyRepositoryImpl(private val api :RetrofitApi):CountryRepository {
    override suspend fun getCountries(): CountryResponse {
        return api.getCountries()
    }
}