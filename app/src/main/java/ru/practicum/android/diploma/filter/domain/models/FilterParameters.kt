package ru.practicum.android.diploma.filter.domain.models
import ru.practicum.android.diploma.filter.data.models.FilterParametersDto

data class FilterParameters(
    val salary: Int,
    val area: String,
    val industry: String,
    val onlyWithSalary: Boolean,
) {
    fun toDto(): FilterParametersDto {
        return FilterParametersDto(
            salary = this.salary,
            area = this.area,
            industry = this.industry,
            onlyWithSalary = this.onlyWithSalary
        )
    }
}