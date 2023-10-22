package ru.practicum.android.diploma.filter.domain.models.fields

import ru.practicum.android.diploma.filter.data.network.dto.fields.CountryDto
import ru.practicum.android.diploma.search.domain.models.fields.Area
import ru.practicum.android.diploma.search.domain.models.fields.toDto

data class Country(
    val id: String?,
    val name: String?,
    val areas: List<Area>?
)

fun Country.toDto(): CountryDto {
    return CountryDto(
        id = this.id,
        name = this.name,
        areas = this.areas?.map { it.toDto() }
    )
}
