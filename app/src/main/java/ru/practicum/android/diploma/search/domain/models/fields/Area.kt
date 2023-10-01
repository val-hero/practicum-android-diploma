package ru.practicum.android.diploma.search.domain.models.fields

import ru.practicum.android.diploma.search.data.network.dto.fields.AreaDto

data class Area(
    val id: Long?,
    val name: String?
) {
    fun toDto(): AreaDto {
        return AreaDto(
            id = this.id,
            name = this.name
        )
    }
}