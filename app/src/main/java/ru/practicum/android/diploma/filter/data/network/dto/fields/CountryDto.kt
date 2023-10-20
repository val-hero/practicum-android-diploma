package ru.practicum.android.diploma.filter.data.network.dto.fields

import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.search.data.network.dto.fields.AreaDto
import ru.practicum.android.diploma.search.data.network.dto.fields.toDomain

data class CountryDto(
    val id: String?,
    val name: String?,
    val areas: List<AreaDto>?
)

fun CountryDto.toDomain(): Country {
    return Country(
        id = this.id,
        name = this.name,
        areas = this.areas?.map { it.toDomain() }
    )
}
