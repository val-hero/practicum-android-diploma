package ru.practicum.android.diploma.filter.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.models.fields.Industry

interface IndustryRepository {
    suspend fun getIndustry(): Flow<Resource<List<Industry>>>
}