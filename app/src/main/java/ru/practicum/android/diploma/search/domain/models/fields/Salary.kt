package ru.practicum.android.diploma.search.domain.models.fields

data class Salary(
    val currency: String?,
    val from: Long?,
    val gross: Boolean?,
    val to: String?
)
