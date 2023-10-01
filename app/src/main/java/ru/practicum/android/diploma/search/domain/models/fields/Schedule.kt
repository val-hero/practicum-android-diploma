package ru.practicum.android.diploma.search.domain.models.fields

import ru.practicum.android.diploma.search.data.network.dto.fields.ScheduleDto

data class Schedule(
    val id: String?,
    val name: String?,
) {
    fun toDto(): ScheduleDto {
        return ScheduleDto(
            id = this.id,
            name = this.name
        )
    }
}
