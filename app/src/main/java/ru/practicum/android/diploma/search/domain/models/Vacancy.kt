package ru.practicum.android.diploma.search.domain.models

import ru.practicum.android.diploma.search.domain.models.fields.Area
import ru.practicum.android.diploma.search.domain.models.fields.Employer
import ru.practicum.android.diploma.search.domain.models.fields.Salary
import java.io.Serializable

data class Vacancy(
    val id: String,
    val area: Area,
    val employer: Employer,
    val name: String,
    val salary: Salary?,
): Serializable