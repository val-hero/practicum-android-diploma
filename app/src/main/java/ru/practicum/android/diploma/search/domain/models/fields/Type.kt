package ru.practicum.android.diploma.search.domain.models.fields

import ru.practicum.android.diploma.search.data.network.dto.fields.TypeDto

data class Type(
    val id: String?,
    val name: String?
) {
    fun toDto(): TypeDto {
        return TypeDto(
            id = this.id,
            name = this.name
        )
    }
}