package ru.practicum.android.diploma.filter.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.data.network.dto.fields.toDomain
import ru.practicum.android.diploma.filter.domain.IndustryRepository
import ru.practicum.android.diploma.filter.domain.models.fields.Industry
import ru.practicum.android.diploma.search.data.network.api.HeadHunterApiService

class IndustryRepositoryImpl(private val api: HeadHunterApiService) : IndustryRepository {
    override suspend fun getIndustry(): Flow<Resource<List<Industry>>> = flow {
        try {
            val industry = api.getIndustries().map { it.toDomain() }
            emit(Resource.Success(industry))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.NO_CONNECTION))
        }
    }
}