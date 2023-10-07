package ru.practicum.android.diploma.search.domain.models.fields

import ru.practicum.android.diploma.search.data.network.dto.EmploymentDto

data class Employment(
    val id: String,
    val name: String
) {
    fun toDto(): EmploymentDto {
        return EmploymentDto(
            id = this.id,
            name = this.name
        )
    }
}
