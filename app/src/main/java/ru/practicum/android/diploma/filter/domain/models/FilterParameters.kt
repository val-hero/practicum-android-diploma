package ru.practicum.android.diploma.filter.domain.models
import ru.practicum.android.diploma.filter.data.models.FilterParametersDto
import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.filter.domain.models.fields.Industry
import ru.practicum.android.diploma.filter.domain.models.fields.toDto
import ru.practicum.android.diploma.search.domain.models.fields.Area
import ru.practicum.android.diploma.search.domain.models.fields.toDto

data class FilterParameters(
    var salary: Int?,
    var area: Area?,
    var country: Country?,
    var industry: Industry?,
    var onlyWithSalary: Boolean?,
) {
    fun toDto(): FilterParametersDto {
        return FilterParametersDto(
            salary = this.salary,
            area = this.area?.toDto(),
            country = this.country?.toDto(),
            industry = this.industry?.toDto(),
            onlyWithSalary = this.onlyWithSalary
        )
    }
}