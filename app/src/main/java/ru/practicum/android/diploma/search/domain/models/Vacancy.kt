package ru.practicum.android.diploma.search.domain.models

import ru.practicum.android.diploma.search.data.local.entity.VacancyEntity
import ru.practicum.android.diploma.search.data.network.dto.VacancyDto
import ru.practicum.android.diploma.search.domain.models.fields.Address
import ru.practicum.android.diploma.search.domain.models.fields.Contacts
import ru.practicum.android.diploma.search.domain.models.fields.Department
import ru.practicum.android.diploma.search.domain.models.fields.DriverLicenseType
import ru.practicum.android.diploma.search.domain.models.fields.Employer
import ru.practicum.android.diploma.search.domain.models.fields.Employment
import ru.practicum.android.diploma.search.domain.models.fields.Experience
import ru.practicum.android.diploma.search.domain.models.fields.KeySkill
import ru.practicum.android.diploma.search.domain.models.fields.Languages
import ru.practicum.android.diploma.search.domain.models.fields.ProfessionalRoles
import ru.practicum.android.diploma.search.domain.models.fields.Salary
import ru.practicum.android.diploma.search.domain.models.fields.Schedule
import ru.practicum.android.diploma.search.domain.models.fields.Type
import ru.practicum.android.diploma.search.domain.models.fields.WorkingDays
import ru.practicum.android.diploma.search.domain.models.fields.WorkingTimeIntervals
import ru.practicum.android.diploma.search.domain.models.fields.WorkingTimeModels

data class Vacancy(
    val address: Address?,
    val contacts: Contacts?,
    val department: Department,
    val driverLicense: List<DriverLicenseType>?,
    val employer: Employer?,
    val employment: Employment?,
    val experience: Experience?,
    val id: Long?,
    val keySkills: List<KeySkill>?,
    val languages: Languages?,
    val name: String,
    val professionalRoles: ProfessionalRoles?,
    val salary: Salary?,
    val schedule: Schedule?,
    val type: Type?,
    val workingDays: WorkingDays?,
    val workingTimeIntervals: WorkingTimeIntervals?,
    val workingTimeModels: WorkingTimeModels?,
) {
    fun Vacancy.toEntity(): VacancyEntity {
        return VacancyEntity(
            address = this.address,
            contacts = this.contacts,
            department = this.department,
            driverLicense = this.driverLicense,
            employer = this.employer,
            employment = this.employment,
            experience = this.experience,
            id = this.id,
            keySkills = this.keySkills,
            languages = this.languages,
            name = this.name,
            professionalRoles = this.professionalRoles,
            salary = this.salary,
            schedule = this.schedule,
            type = this.type,
            workingDays = this.workingDays,
            workingTimeIntervals = this.workingTimeIntervals,
            workingTimeModels = this.workingTimeModels
        )
    }

    fun Vacancy.toDto(): VacancyDto {
        return VacancyDto(
            address = this.address,
            contacts = this.contacts,
            department = this.department,
            driverLicense = this.driverLicense,
            employer = this.employer,
            employment = this.employment,
            experience = this.experience,
            id = this.id,
            keySkills = this.keySkills,
            languages = this.languages,
            name = this.name,
            professionalRoles = this.professionalRoles,
            salary = this.salary,
            schedule = this.schedule,
            type = this.type,
            workingDays = this.workingDays,
            workingTimeIntervals = this.workingTimeIntervals,
            workingTimeModels = this.workingTimeModels
        )
    }
}
