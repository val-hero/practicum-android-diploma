package ru.practicum.android.diploma.search.domain.models

import ru.practicum.android.diploma.search.domain.models.fields.Area
import ru.practicum.android.diploma.search.domain.models.fields.Contacts
import ru.practicum.android.diploma.search.domain.models.fields.Employer
import ru.practicum.android.diploma.search.domain.models.fields.Employment
import ru.practicum.android.diploma.search.domain.models.fields.Experience
import ru.practicum.android.diploma.search.domain.models.fields.KeySkills
import ru.practicum.android.diploma.search.domain.models.fields.Salary
import ru.practicum.android.diploma.search.domain.models.fields.Schedule

data class VacancyDetails(
    val id: String,
    val name: String,
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


