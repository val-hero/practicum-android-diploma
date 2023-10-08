package ru.practicum.android.diploma.filter.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.AreasRepository
import ru.practicum.android.diploma.search.domain.models.fields.Area

class GetAreasUseCase(private val areasRepository: AreasRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Area>>> {
        return areasRepository.getAreas()
    }
}