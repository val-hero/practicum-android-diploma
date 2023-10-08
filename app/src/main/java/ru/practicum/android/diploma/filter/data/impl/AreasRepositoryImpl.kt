package ru.practicum.android.diploma.filter.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.AreasRepository
import ru.practicum.android.diploma.search.data.network.api.RetrofitApi
import ru.practicum.android.diploma.search.domain.models.fields.Area

class AreasRepositoryImpl(private val api: RetrofitApi) : AreasRepository {
    override suspend fun getAreas(): Flow<Resource<List<Area>>> = flow {
        try {
            val areas = api.getAreas().map { it.toArea() }
            emit(Resource.Success(areas))
        } catch (e: Exception) {

        }
    }
}