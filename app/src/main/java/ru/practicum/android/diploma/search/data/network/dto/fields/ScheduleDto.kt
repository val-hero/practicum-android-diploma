package ru.practicum.android.diploma.search.data.network.dto.fields

import ru.practicum.android.diploma.search.domain.models.fields.Schedule

data class ScheduleDto(
    val id: String?,
    val name: String
)

fun ScheduleDto.toDomain(): Schedule {
    return Schedule(
        id = this.id,
        name = this.name
    )
}