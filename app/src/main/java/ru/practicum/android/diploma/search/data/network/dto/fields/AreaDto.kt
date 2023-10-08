package ru.practicum.android.diploma.search.data.network.dto.fields

import ru.practicum.android.diploma.search.domain.models.fields.Area

data class AreaDto(
    val id: String?,
    val name: String?,
    val countryId: String?
) {
    fun AreaDto.toDomain(): Area {
        return Area(
            id = this.id,
            name = this.name,
            countryId=this.countryId
        )
    }
}