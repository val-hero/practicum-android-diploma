package ru.practicum.android.diploma.filter.domain.models.fields

import ru.practicum.android.diploma.filter.data.network.dto.fields.IndustryDto

data class Industry(
    val id: String,
    val name: String,
    val parentId: String?,
    val industries: List<Industry>?,
)

fun Industry.toDto(): IndustryDto {
    return IndustryDto(
        id = this.id,
        name = this.name,
        parentId = this.parentId,
        industries = this.industries?.map { it.toDto() }
    )
}

