package ru.practicum.android.diploma.filter.domain

import ru.practicum.android.diploma.filter.data.network.CountryResponse

interface CountryRepository {
    suspend fun getCountries(): CountryResponse
}