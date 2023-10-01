package ru.practicum.android.diploma.search.data.network.dto.fields

import ru.practicum.android.diploma.search.domain.models.fields.Type

data class TypeDto(
    val id: String?,
    val name: String?
) {
    fun toType(): Type {
        return Type(
            id = this.id,
            name = this.name
        )
    }
}