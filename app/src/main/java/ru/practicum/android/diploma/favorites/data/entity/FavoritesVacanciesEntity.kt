package ru.practicum.android.diploma.favorites.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.search.domain.models.fields.Area
import ru.practicum.android.diploma.search.domain.models.fields.Contacts
import ru.practicum.android.diploma.search.domain.models.fields.Employer
import ru.practicum.android.diploma.search.domain.models.fields.Employment
import ru.practicum.android.diploma.search.domain.models.fields.Experience
import ru.practicum.android.diploma.search.domain.models.fields.KeySkill
import ru.practicum.android.diploma.search.domain.models.fields.Salary
import ru.practicum.android.diploma.search.domain.models.fields.Schedule
import ru.practicum.android.diploma.search.domain.models.fields.Type
import java.util.Calendar

@Entity(tableName = "favorites_vacancy_table")
data class FavoritesVacanciesEntity(
    val area: Area?,
    val contacts: Contacts?,
    val description: String?,
    val employer: Employer?,
    val employment: Employment?,
    val experience: Experience?,
    @PrimaryKey
    val id: Long?,
    val keySkills: List<KeySkill>?,
    val name: String,
    val salary: Salary?,
    val schedule: Schedule?,
    val type: Type?,
    val saveDate: Long
)

