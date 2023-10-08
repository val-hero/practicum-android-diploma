package ru.practicum.android.diploma.filter.data.models

import ru.practicum.android.diploma.filter.data.network.dto.fields.CountryDto
import ru.practicum.android.diploma.filter.data.network.dto.fields.IndustryDto
import ru.practicum.android.diploma.filter.data.network.dto.fields.toDomain
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.search.data.network.dto.fields.AreaDto
import ru.practicum.android.diploma.search.data.network.dto.fields.toDomain

data class FilterParametersDto(
    var salary: Int?,
    var area: AreaDto?,
    var country: CountryDto?,
    var industry: IndustryDto?,
    var onlyWithSalary: Boolean?
) {
    fun toDomain(): FilterParameters {
        return FilterParameters(
            salary = this.salary,
            area = this.area?.toDomain(),
            country = this.country?.toDomain(),
            industry = this.industry?.toDomain(),
            onlyWithSalary = this.onlyWithSalary
        )
    }
}