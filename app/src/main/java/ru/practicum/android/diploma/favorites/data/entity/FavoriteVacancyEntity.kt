package ru.practicum.android.diploma.favorites.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.practicum.android.diploma.search.domain.models.VacancyDetails
import ru.practicum.android.diploma.search.domain.models.fields.Area
import ru.practicum.android.diploma.search.domain.models.fields.Contacts
import ru.practicum.android.diploma.search.domain.models.fields.Employer
import ru.practicum.android.diploma.search.domain.models.fields.Employment
import ru.practicum.android.diploma.search.domain.models.fields.Experience
import ru.practicum.android.diploma.search.domain.models.fields.KeySkills
import ru.practicum.android.diploma.search.domain.models.fields.Salary
import ru.practicum.android.diploma.search.domain.models.fields.Schedule

@Entity(tableName = "favorites_vacancy_table")
data class FavoriteVacancyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val area: Area?,
    val contacts: Contacts?,
    val description: String?,
    val employer: Employer?,
    val employment: Employment?,
    val experience: Experience?,
    val keySkills: List<KeySkills>?,
    val name: String?,
    val salary: Salary?,
    val schedule: Schedule?,
    val alternateUrl: String?,
    val saveDate: Long
)

fun FavoriteVacancyEntity.toDomain(): VacancyDetails {
    return VacancyDetails(
        id = this.id,
        name = this.name,
        area = this.area,
        contacts = this.contacts,
        description = this.description,
        employer = this.employer,
        employment = this.employment,
        experience = this.experience,
        keySkills = this.keySkills,
        salary = this.salary,
        schedule = this.schedule,
        alternateUrl = this.alternateUrl
    )
}

