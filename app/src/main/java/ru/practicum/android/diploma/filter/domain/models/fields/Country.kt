package ru.practicum.android.diploma.filter.domain.models.fields

import ru.practicum.android.diploma.filter.data.network.dto.fields.CountryDto

data class Country(
    val id: String?,
    val name: String?
)

fun Country.toDto(): CountryDto {
    return CountryDto(
        id = this.id,
        name = this.name
    )
}
