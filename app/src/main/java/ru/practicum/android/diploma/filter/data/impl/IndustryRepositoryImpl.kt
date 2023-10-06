package ru.practicum.android.diploma.filter.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.IndustryRepository
import ru.practicum.android.diploma.filter.domain.models.fields.Industry
import ru.practicum.android.diploma.search.data.network.api.RetrofitApi

class IndustryRepositoryImpl(private val api : RetrofitApi):IndustryRepository {
    override suspend fun getIndustry(): Flow<Resource<List<Industry>>> = flow {
        try {
            val industry = api.getIndustries().map { it.toIndustry()}
            emit(Resource.Success(industry))
        } catch (e: Exception) {

        }
    }
}