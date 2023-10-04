package ru.practicum.android.diploma.search.domain.usecase

import ru.practicum.android.diploma.search.domain.api.SearchRepository

class GetVacancyDetailsUseCase(private val searchRepository: SearchRepository) {

    suspend operator fun invoke(id: String) = searchRepository.getVacancy(id)
}