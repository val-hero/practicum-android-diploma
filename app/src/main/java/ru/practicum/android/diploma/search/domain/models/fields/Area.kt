package ru.practicum.android.diploma.search.domain.models.fields

import ru.practicum.android.diploma.search.data.network.dto.AreaDto

data class Area(
    val id: String,
    val name: String
) {
    fun toDto(): AreaDto {
        return AreaDto(
            id = this.id,
            name = this.name,
            url = ""
        )
    }
}