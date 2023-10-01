package ru.practicum.android.diploma.search.domain.models

import ru.practicum.android.diploma.search.data.local.entity.VacancyEntity
import ru.practicum.android.diploma.search.data.network.dto.VacancyDto
import ru.practicum.android.diploma.search.domain.models.fields.Area
import ru.practicum.android.diploma.search.domain.models.fields.Contacts
import ru.practicum.android.diploma.search.domain.models.fields.Employer
import ru.practicum.android.diploma.search.domain.models.fields.Employment
import ru.practicum.android.diploma.search.domain.models.fields.Experience
import ru.practicum.android.diploma.search.domain.models.fields.KeySkill
import ru.practicum.android.diploma.search.domain.models.fields.Salary
import ru.practicum.android.diploma.search.domain.models.fields.Schedule
import ru.practicum.android.diploma.search.domain.models.fields.Type

data class Vacancy(
    val area: Area?,
    val contacts: Contacts?,
    val description: String?,
    val employer: Employer?,
    val employment: Employment?,
    val experience: Experience?,
    val id: Long?,
    val keySkills: List<KeySkill>?,
    val name: String,
    val salary: Salary?,
    val schedule: Schedule?,
    val type: Type?,
    ) {
    fun Vacancy.toEntity(): VacancyEntity {
        return VacancyEntity(
            area = this.area,
            contacts = this.contacts,
            description = this.description,
            employer = this.employer,
            employment = this.employment,
            experience = this.experience,
            id = this.id,
            keySkills = this.keySkills,
            name = this.name,
            salary = this.salary,
            schedule = this.schedule,
            type = this.type
        )
    }

    fun Vacancy.toDto(): VacancyDto {
        return VacancyDto(
            area = this.area,
            contacts = this.contacts,
            description = this.description,
            employer = this.employer,
            employment = this.employment,
            experience = this.experience,
            id = this.id,
            keySkills = this.keySkills,
            name = this.name,
            salary = this.salary,
            schedule = this.schedule,
            type = this.type
        )
    }
}
