package ru.practicum.android.diploma.search.data.network.dto.fields

import ru.practicum.android.diploma.search.domain.models.fields.Area

data class AreaDto(
    val id: Long?,
    val name: String?
) {
    fun toArea(): Area {
        return Area(
            id = this.id,
            name = this.name
        )
    }
}