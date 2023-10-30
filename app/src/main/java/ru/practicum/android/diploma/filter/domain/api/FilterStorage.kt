package ru.practicum.android.diploma.filter.domain.api

import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.filter.domain.models.fields.Industry
import ru.practicum.android.diploma.search.domain.models.fields.Area

interface FilterStorage {
    suspend fun getParams(): FilterParameters?

    suspend fun saveCountry(country: Country?)

    suspend fun saveArea(area: Area?)

    suspend fun saveIndustry(industry: Industry?)

    suspend fun saveSalary(salary: Int?)

    suspend fun saveSalaryFlag(onlyWithSalary: Boolean?)

    suspend fun clearFilterSettings()

    suspend fun restoreFilterSettings(filterParameters: FilterParameters?)

}