package ru.practicum.android.diploma.filter.domain.usecase

import ru.practicum.android.diploma.filter.domain.api.FilterStorage
import ru.practicum.android.diploma.search.domain.models.fields.Area

class SaveAreaUseCase(private val filterStorage: FilterStorage) {
    suspend operator fun invoke(area: Area?) {
        return filterStorage.saveArea(area)
    }
}