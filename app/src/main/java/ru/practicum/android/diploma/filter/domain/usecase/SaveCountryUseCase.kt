package ru.practicum.android.diploma.filter.domain.usecase

import ru.practicum.android.diploma.filter.domain.api.FilterStorage
import ru.practicum.android.diploma.filter.domain.models.fields.Country

class SaveCountryUseCase(private val filterStorage: FilterStorage) {
    suspend operator fun invoke(country: Country?) {
        return filterStorage.saveCountry(country)
    }
}