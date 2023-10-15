package ru.practicum.android.diploma.search.domain.usecase

import ru.practicum.android.diploma.search.domain.api.SearchRepository

class SearchUseCase(private val searchRepository: SearchRepository) {

    suspend operator fun invoke(parameters : HashMap<String,Any>) = searchRepository.getVacancies(parameters)
}