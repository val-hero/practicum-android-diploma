package ru.practicum.android.diploma.search.domain.models.fields

import ru.practicum.android.diploma.search.data.network.dto.fields.KeySkillDto

data class KeySkill(
    val name: String?,
) {
    fun toDto(): KeySkillDto {
        return KeySkillDto(
            name = this.name
        )
    }
}
