package ru.practicum.android.diploma.filter.data.models

import ru.practicum.android.diploma.filter.domain.models.FilterParameters

data class FilterParametersDto(
    val salary: Int,
    val area: String,
    val industry: String,
    val onlyWithSalary: Boolean,
) {
    fun toDomain(): FilterParameters {
        return FilterParameters(
            salary = this.salary,
            area = this.area,
            industry = this.industry,
            onlyWithSalary = this.onlyWithSalary
        )
    }
}