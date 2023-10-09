package ru.practicum.android.diploma.search.domain.models

import ru.practicum.android.diploma.favorites.data.entity.FavoriteVacancyEntity
import ru.practicum.android.diploma.search.domain.models.fields.Area
import ru.practicum.android.diploma.search.domain.models.fields.Contacts
import ru.practicum.android.diploma.search.domain.models.fields.Employer
import ru.practicum.android.diploma.search.domain.models.fields.Employment
import ru.practicum.android.diploma.search.domain.models.fields.Experience
import ru.practicum.android.diploma.search.domain.models.fields.KeySkills
import ru.practicum.android.diploma.search.domain.models.fields.Salary
import ru.practicum.android.diploma.search.domain.models.fields.Schedule
import java.util.Calendar

data class VacancyDetails(
    val id: String,
    val name: String?,
    val area: Area?,
    val contacts: Contacts?,
    val description: String?,
    val employer: Employer?,
    val employment: Employment?,
    val experience: Experience?,
    val keySkills: List<KeySkills>?,
    val salary: Salary?,
    val schedule: Schedule?,
)

fun VacancyDetails.toFavoriteEntity(): FavoriteVacancyEntity {
    return FavoriteVacancyEntity(
        id = this.id,
        area = this.area,
        contacts = this.contacts,
        description = this.description,
        employer = this.employer,
        employment = this.employment,
        experience = this.experience,
        keySkills = this.keySkills,
        name = this.name,
        salary = this.salary,
        schedule = this.schedule,
        saveDate = Calendar.getInstance().timeInMillis
    )
}


