package ru.practicum.android.diploma.filter.domain


import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.models.fields.Country

interface CountryRepository {
    suspend fun getCountries(): Flow<Resource<List<Country>>>

}