package ru.practicum.android.diploma.search.data.network.dto.fields

import ru.practicum.android.diploma.search.domain.models.fields.KeySkill

data class KeySkillDto(
    val name: String?,
) {
    fun toKeySkill(): KeySkill {
        return KeySkill(
            name = this.name
        )
    }
}
