package ru.practicum.android.diploma.filter.domain.models
import ru.practicum.android.diploma.filter.data.models.FilterParametersDto

data class FilterParameters(
    var salary: Int?,
    var area: String?,
    var country: String?,
    var industry: String?,
    var onlyWithSalary: Boolean?,
) {
    fun toDto(): FilterParametersDto {
        return FilterParametersDto(
            salary = this.salary,
            area = this.area,
            country = this.country,
            industry = this.industry,
            onlyWithSalary = this.onlyWithSalary
        )
    }
}