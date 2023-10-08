package ru.practicum.android.diploma.filter.domain.models.fields

import ru.practicum.android.diploma.filter.data.network.dto.feilds.IndustryDto

data class Industry(
    val id: String,
    val name: String,
    val parentId: String?
) {
    fun toDto(): IndustryDto {
        return IndustryDto(
            id = this.id,
            name = this.name,
            parentId = this.parentId
        )
    }
}
