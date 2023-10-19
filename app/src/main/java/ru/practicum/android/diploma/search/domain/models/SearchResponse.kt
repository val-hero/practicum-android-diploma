package ru.practicum.android.diploma.search.domain.models

data class SearchResponse(
    val found: Int,
    val vacancies: List<Vacancy>,
    val page: Int,
    val pages: Int,
    val perPage: Int,
)



