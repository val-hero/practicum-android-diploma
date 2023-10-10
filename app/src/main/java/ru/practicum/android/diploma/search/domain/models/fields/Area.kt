package ru.practicum.android.diploma.search.domain.models.fields

import ru.practicum.android.diploma.search.data.network.dto.fields.AreaDto

data class Area(
    val id: String?,
    val name: String?,
    val countryId: String?,
    val areas: List<Area>?
)

fun Area.toDto(): AreaDto {
    return AreaDto(
        id = this.id,
        name = this.name,
        countryId = this.countryId,
        areas = this.areas?.map { it.toDto() }
    )
}