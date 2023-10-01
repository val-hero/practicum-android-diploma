package ru.practicum.android.diploma.search.domain.models.fields

import ru.practicum.android.diploma.search.data.network.dto.fields.ExperienceDto

data class Experience(
    val id: String?,
    val name: String?
) {
    fun toDto(): ExperienceDto {
        return ExperienceDto(
            id = this.id,
            name = this.name
        )
    }
}
