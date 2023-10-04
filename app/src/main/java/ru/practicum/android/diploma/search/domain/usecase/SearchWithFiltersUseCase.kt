package ru.practicum.android.diploma.search.domain.usecase

import ru.practicum.android.diploma.search.domain.api.SearchRepository

class SearchWithFiltersUseCase(private val searchRepository: SearchRepository) {

    suspend operator fun invoke(filters: HashMap<String, String>) =
        searchRepository.getVacanciesWithFilter(filters)

}