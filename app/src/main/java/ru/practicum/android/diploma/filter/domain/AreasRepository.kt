package ru.practicum.android.diploma.filter.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.search.domain.models.fields.Area

interface AreasRepository {
    suspend fun getAreas(): Flow<Resource<List<Area>>>

    suspend fun getAreasInCountry(countryId: String): Flow<Resource<List<Area>>>
}