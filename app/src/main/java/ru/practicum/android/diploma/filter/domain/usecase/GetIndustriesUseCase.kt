package ru.practicum.android.diploma.filter.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.IndustryRepository
import ru.practicum.android.diploma.filter.domain.models.fields.Industry

class GetIndustriesUseCase(private val industryRepository: IndustryRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Industry>>>{
        return industryRepository.getIndustry()
    }
}