package ru.practicum.android.diploma.search.data.network.dto.fields

import ru.practicum.android.diploma.search.domain.models.fields.Employment

data class EmploymentDto(
    val id: String,
    val name: String
)

fun EmploymentDto.toDomain(): Employment {
    return Employment(
        id = this.id,
        name = this.name
    )
}