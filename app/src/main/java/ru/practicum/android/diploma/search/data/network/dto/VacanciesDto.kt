package ru.practicum.android.diploma.search.data.network.dto

data class VacanciesDto(
    val alternate_url: String,
    val found: Int,
    val items: List<VacancyDto>,
    val page: Int,
    val pages: Int,
    val per_page: Int,
)