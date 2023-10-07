package ru.practicum.android.diploma.search.data.network.dto.fields

import ru.practicum.android.diploma.search.domain.models.fields.Experience

data class ExperienceDto(
    val id: String,
    val name: String
)

fun ExperienceDto.toDomain(): Experience {
    return Experience(
        id = this.id,
        name = this.id
    )
}