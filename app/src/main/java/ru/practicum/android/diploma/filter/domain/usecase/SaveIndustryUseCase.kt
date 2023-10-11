package ru.practicum.android.diploma.filter.domain.usecase

import ru.practicum.android.diploma.filter.domain.api.FilterStorage
import ru.practicum.android.diploma.filter.domain.models.fields.Industry

class SaveIndustryUseCase(private val filterStorage: FilterStorage) {
    suspend operator fun invoke(industry: Industry) {
        return filterStorage.saveIndustry(industry)
    }
}