package ru.practicum.android.diploma.filter.domain.api

import ru.practicum.android.diploma.filter.domain.models.FilterParameters

interface FilterStorage {
    suspend fun getParams(): FilterParameters?

    suspend fun saveCountry(country: String)

    suspend fun saveArea(area: String)

    suspend fun saveIndustry(industry: String)

    suspend fun saveSalary(salary: Int)

    suspend fun saveSalaryFlag(onlyWithSalary: Boolean)

}