package ru.practicum.android.diploma.search.domain.models.fields

data class Employer(
    val id: String,
    val logoUrls: EmployerLogoUrls?,
    val name: String,
    val url: String,
)