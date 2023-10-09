package ru.practicum.android.diploma.search.data.network.dto.fields

import ru.practicum.android.diploma.search.domain.models.fields.KeySkills

data class KeySkillsDto(
    val name: String?
)

fun KeySkillsDto.toDomain(): KeySkills {
    return KeySkills(
        name = this.name
    )
}