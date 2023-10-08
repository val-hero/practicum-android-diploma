package ru.practicum.android.diploma.filter.domain.usecase

import ru.practicum.android.diploma.filter.domain.api.FilterStorage

class SaveSalaryFlagUseCase(private val filterStorage: FilterStorage) {
    suspend operator fun invoke(flag: Boolean) {
        return filterStorage.saveSalaryFlag(flag)
    }
}