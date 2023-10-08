package ru.practicum.android.diploma.search.data.network.dto

import ru.practicum.android.diploma.search.data.network.dto.fields.AreaDto
import ru.practicum.android.diploma.search.data.network.dto.fields.ContactsDto
import ru.practicum.android.diploma.search.data.network.dto.fields.EmployerDto
import ru.practicum.android.diploma.search.data.network.dto.fields.EmploymentDto
import ru.practicum.android.diploma.search.data.network.dto.fields.ExperienceDto
import ru.practicum.android.diploma.search.data.network.dto.fields.KeySkillsDto
import ru.practicum.android.diploma.search.data.network.dto.fields.SalaryDto
import ru.practicum.android.diploma.search.data.network.dto.fields.ScheduleDto
import ru.practicum.android.diploma.search.data.network.dto.fields.toDomain
import ru.practicum.android.diploma.search.domain.models.VacancyDetails

data class VacancyDetailsDto(
    val id: String,
    val area: AreaDto,
    val employer: EmployerDto,
    val name: String,
    val salary: SalaryDto?,
    val description: String,
    val employment: EmploymentDto,
    val experience: ExperienceDto?,
    val contacts: ContactsDto?,
    val schedule: ScheduleDto?,
    val keySkills: List<KeySkillsDto>
)

fun VacancyDetailsDto.toDomain(): VacancyDetails {
    return VacancyDetails(
        id = this.id,
        name = this.name,
        area = this.area.toDomain(),
        contacts = this.contacts?.toDomain(),
        description = this.description,
        employer = this.employer.toDomain(),
        employment = this.employment.toDomain(),
        experience = this.experience?.toDomain(),
        keySkills = this.keySkills.map { it.toDomain() },
        salary = this.salary?.toDomain(),
        schedule = this.schedule?.toDomain()
    )
}