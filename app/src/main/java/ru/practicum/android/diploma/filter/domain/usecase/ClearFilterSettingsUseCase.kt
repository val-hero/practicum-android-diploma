package ru.practicum.android.diploma.filter.domain.usecase

import ru.practicum.android.diploma.filter.domain.api.FilterStorage

class ClearFilterSettingsUseCase (private val filterStorage: FilterStorage) {
    suspend operator fun invoke() {
        return filterStorage.clearFilterSettings()
    }
}