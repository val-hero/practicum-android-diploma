package ru.practicum.android.diploma.search.data.network.dto

data class EmployerDto(
    val accredited_it_employer: Boolean,
    val alternate_url: String,
    val id: String,
    val logo_urls: LogoUrlsDto,
    val name: String,
    val trusted: Boolean,
    val url: String,
    val vacancies_url: String
)