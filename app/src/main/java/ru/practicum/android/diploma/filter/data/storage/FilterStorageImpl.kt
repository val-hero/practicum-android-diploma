package ru.practicum.android.diploma.filter.data.storage

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.core.utils.Constants.FILTER_PARAMETERS
import ru.practicum.android.diploma.filter.data.models.FilterParametersDto
import ru.practicum.android.diploma.filter.domain.api.FilterStorage
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.filter.domain.models.fields.Industry
import ru.practicum.android.diploma.filter.domain.models.fields.toDto
import ru.practicum.android.diploma.search.domain.models.fields.Area
import ru.practicum.android.diploma.search.domain.models.fields.toDto

class FilterStorageImpl(private val sharedPref: SharedPreferences, private val gson: Gson) :
    FilterStorage {

    override suspend fun saveCountry(country: Country) {
        val params = getParamsOrCreateFilter()
        params.country = country.toDto()
        updateField(params)
    }

    override suspend fun saveArea(area: Area) {
        val params = getParamsOrCreateFilter()
        params.area = area.toDto()
        updateField(params)
    }

    override suspend fun saveIndustry(industry: Industry) {
        val params = getParamsOrCreateFilter()
        params.industry = industry.toDto()
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
    private suspend fun updateField(params: FilterParametersDto?) {
        val json = gson.toJson(params)
        sharedPref.edit()
            .putString(FILTER_PARAMETERS, json)
            .apply()
    }
}