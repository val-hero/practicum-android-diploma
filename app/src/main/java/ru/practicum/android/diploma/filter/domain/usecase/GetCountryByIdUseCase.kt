package ru.practicum.android.diploma.filter.domain.usecase

import ru.practicum.android.diploma.filter.domain.CountryRepository
import ru.practicum.android.diploma.filter.domain.models.fields.Country

class GetCountryByIdUseCase(private val countryRepository: CountryRepository) {
    suspend operator fun invoke(countryId: String): Country {
        return countryRepository.getCountryById(countryId)
    }
}