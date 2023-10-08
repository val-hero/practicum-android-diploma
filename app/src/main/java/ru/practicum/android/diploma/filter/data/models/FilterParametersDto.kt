package ru.practicum.android.diploma.filter.data.models

import ru.practicum.android.diploma.filter.domain.models.FilterParameters

data class FilterParametersDto(
    var salary: Int?,
    var area: String?,
    var country: String?,
    var industry: String?,
    var onlyWithSalary: Boolean?
) {
    fun toDomain(): FilterParameters {
        return FilterParameters(
            salary = this.salary,
            area = this.area,
            country = this.country,
            industry = this.industry,
            onlyWithSalary = this.onlyWithSalary
        )
    }
}