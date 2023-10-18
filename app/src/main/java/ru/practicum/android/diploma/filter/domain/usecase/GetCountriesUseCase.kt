package ru.practicum.android.diploma.filter.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.filter.domain.CountryRepository
import ru.practicum.android.diploma.filter.domain.models.fields.Country

class GetCountriesUseCase(private val repository: CountryRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Country>>> {
        return repository.getCountries()
    }
}