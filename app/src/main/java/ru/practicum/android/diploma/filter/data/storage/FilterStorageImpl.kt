package ru.practicum.android.diploma.filter.data.storage

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.core.utils.Constants.FILTER_PARAMETERS
import ru.practicum.android.diploma.filter.data.models.FilterParametersDto
import ru.practicum.android.diploma.filter.domain.api.FilterStorage
import ru.practicum.android.diploma.filter.domain.models.FilterParameters

class FilterStorageImpl(private val sharedPref: SharedPreferences, private val gson: Gson) :
    FilterStorage {

    override suspend fun saveCountry(country: String) {
        val params = getParamsOrCreateFilter()
        params.country = country
        updateField(params)
    }

    override suspend fun saveArea(area: String) {
        val params = getParamsOrCreateFilter()
        params.area = area
        updateField(params)
    }

    override suspend fun saveIndustry(industry: String) {
        val params = getParamsOrCreateFilter()
        params.industry = industry
        updateField(params)
    }

    override suspend fun saveSalary(salary: Int) {
        val params = getParamsOrCreateFilter()
        params.salary = salary
        updateField(params)
    }

    override suspend fun saveSalaryFlag(onlyWithSalary: Boolean) {
        val params = getParamsOrCreateFilter()
        params.onlyWithSalary = onlyWithSalary
        updateField(params)
    }

    override suspend fun getParams(): FilterParameters? {
        val json = sharedPref.getString(FILTER_PARAMETERS, null) ?: return null
        val filterParametersDto =
            gson.fromJson(json, FilterParametersDto::class.java)
        return filterParametersDto.toDomain()
    }

    private suspend fun getParamsOrCreateFilter(): FilterParametersDto {
        val params = if (getParams() != null) {
            getParams()!!.toDto()
        } else {
            FilterParametersDto(null, null, null, null, null)
        }
        return params
    }
    private fun updateField(params: FilterParametersDto?) {
        val json = gson.toJson(params)
        sharedPref.edit()
            .putString(FILTER_PARAMETERS, json)
            .apply()
    }
}