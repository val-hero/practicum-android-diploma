package ru.practicum.android.diploma.search.data.network.dto

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.search.data.network.dto.fields.AreaDto
import ru.practicum.android.diploma.search.data.network.dto.fields.ContactsDto
import ru.practicum.android.diploma.search.data.network.dto.fields.EmployerDto
import ru.practicum.android.diploma.search.data.network.dto.fields.EmploymentDto
import ru.practicum.android.diploma.search.data.network.dto.fields.ExperienceDto
import ru.practicum.android.diploma.search.data.network.dto.fields.KeySkillDto
import ru.practicum.android.diploma.search.data.network.dto.fields.SalaryDto
import ru.practicum.android.diploma.search.data.network.dto.fields.ScheduleDto
import ru.practicum.android.diploma.search.data.network.dto.fields.TypeDto
import ru.practicum.android.diploma.search.domain.models.Vacancy

data class VacancyDto(
    val area: AreaDto?,
    val contacts: ContactsDto?,
    val description: String?,
    val employer: EmployerDto?,
    val employment: EmploymentDto?,
    val experience: ExperienceDto?,
    val id: Long?,
    @SerializedName("key_skills")
    val keySkills: List<KeySkillDto>?,
    val name: String,
    val salary: SalaryDto?,
    val schedule: ScheduleDto?,
    val type: TypeDto?
)
fun VacancyDto.toVacancy(): Vacancy {
    return Vacancy(
        area = this.area?.toArea(),
        contacts = this.contacts?.toContacts(),
        description = this.description,
        employer = this.employer?.toEmployer(),
        employment = this.employment?.toEmployment(),
        experience = this.experience?.toExperience(),
        id = this.id,
        keySkills = this.keySkills?.map { it.toKeySkill() },
        name = this.name,
        salary = this.salary?.toSalary(),
        schedule = this.schedule?.toSchedule(),
        type = this.type?.toType()
    )
}