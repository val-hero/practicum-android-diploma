package ru.practicum.android.diploma.filter.data.network.dto.fields

import ru.practicum.android.diploma.filter.domain.models.fields.Country

data class CountryDto(
    val id: String?,
    val name: String?
)

fun CountryDto.toDomain(): Country {
    return Country(
        id = this.id,
        name = this.name
    )
}
