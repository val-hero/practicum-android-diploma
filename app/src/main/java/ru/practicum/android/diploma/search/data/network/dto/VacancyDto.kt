package ru.practicum.android.diploma.search.data.network.dto

import ru.practicum.android.diploma.search.data.network.dto.fields.AreaDto
import ru.practicum.android.diploma.search.data.network.dto.fields.EmployerDto
import ru.practicum.android.diploma.search.data.network.dto.fields.SalaryDto
import ru.practicum.android.diploma.search.data.network.dto.fields.toDomain
import ru.practicum.android.diploma.search.domain.models.Vacancy

data class VacancyDto(
    val id: String,
    val area: AreaDto?,
    val employer: EmployerDto?,
    val name: String?,
    val salary: SalaryDto?,
)

fun VacancyDto.toDomain(): Vacancy {
    return Vacancy(
        id = this.id,
        area = this.area?.toDomain(),
        employer = this.employer?.toDomain(),
        name = this.name,
        salary = this.salary?.toDomain()
    )
}
