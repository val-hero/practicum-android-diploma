package ru.practicum.android.diploma.filter.domain.usecase

import ru.practicum.android.diploma.filter.domain.api.FilterStorage
import ru.practicum.android.diploma.filter.domain.models.FilterParameters

class GetFilterSettingsUseCase(private val filterStorage: FilterStorage) {
    suspend operator fun invoke(): FilterParameters? {
        return filterStorage.getParams()
    }
}