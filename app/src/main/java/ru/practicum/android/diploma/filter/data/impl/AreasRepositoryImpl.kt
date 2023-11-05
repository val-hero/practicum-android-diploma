package ru.practicum.android.diploma.filter.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.AreasRepository
import ru.practicum.android.diploma.search.data.network.api.HeadHunterApiService
import ru.practicum.android.diploma.search.data.network.dto.fields.toDomain
import ru.practicum.android.diploma.search.domain.models.fields.Area

class AreasRepositoryImpl(private val api: HeadHunterApiService) : AreasRepository {
    override suspend fun getAreas(): Flow<Resource<List<Area>>> = flow {
        try {
            val areas = api.getAreas().map { it.toDomain() }
            val flatAreas = getFlatAreaList(areas)
            emit(Resource.Success(flatAreas))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.NOT_FOUND))
        }
    }

    override suspend fun getAreasInCountry(countryId: String): Flow<Resource<List<Area>>> = flow {
        try {
            val areas = api.getAreasInCountry(countryId).areas?.map { it.toDomain() }
            val flatAreas = areas?.let { getFlatAreaList(it) }
            emit(Resource.Success(flatAreas as List<Area>))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.NOT_FOUND))
        }
    }

    private fun getFlatAreaList(areaList: List<Area>): List<Area> {
        val flatAreaList = arrayListOf<Area>()
        areaList.forEach {
            flatAreaList.add(it)
            flatAreaList.addAll(flatArea(it))
        }
        val listWithoutCountry = flatAreaList.filter { it.countryId != null }
        return listWithoutCountry.sortedBy { it.name }
    }

    private fun flatArea(area: Area): List<Area> {
        val areaList = arrayListOf<Area>()
        area.areas?.let {
            area.areas.forEach {
                areaList.add(it)
                areaList.addAll(flatArea(it))
            }
        }
        return areaList
    }
}