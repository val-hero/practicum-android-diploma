package ru.practicum.android.diploma.filter.data.storage

import android.content.SharedPreferences
import androidx.core.content.edit
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

    override suspend fun saveCountry(country: Country?) {
        val params = getParamsOrCreateFilter()
        if (country == null) params.country = null else params.country = country.toDto()
        updateField(params)
    }

    override suspend fun saveArea(area: Area?) {
        val params = getParamsOrCreateFilter()
        if (area == null) params.area = null else params.area = area.toDto()
        updateField(params)
    }

    override suspend fun saveIndustry(industry: Industry?) {
        val params = getParamsOrCreateFilter()
        if (industry == null) params.industry = null else params.industry = industry.toDto()
        updateField(params)
    }

    override suspend fun saveSalary(salary: Int?) {
        val params = getParamsOrCreateFilter().apply { this.salary = salary }
        updateField(params)
    }

    override suspend fun saveSalaryFlag(onlyWithSalary: Boolean?) {
        val params = getParamsOrCreateFilter().apply { this.onlyWithSalary = onlyWithSalary }
        updateField(params)
    }

    override suspend fun clearFilterSettings() {
        sharedPref.edit().remove(FILTER_PARAMETERS).apply()
    }

    override suspend fun restoreFilterSettings(filterParameters: FilterParameters?) {
        val json = if (filterParameters != null) gson.toJson(filterParameters.toDto()) else null
        sharedPref.edit()
            .putString(FILTER_PARAMETERS, json)
            .apply()
    }

    override suspend fun getParams(): FilterParameters? {
        val json = sharedPref.getString(FILTER_PARAMETERS, null) ?: return null
        val filterParametersDto =
            gson.fromJson(json, FilterParametersDto::class.java)
        return if (filterParametersDto.country == null &&
            filterParametersDto.area == null &&
            filterParametersDto.industry == null &&
            filterParametersDto.salary == null &&
            filterParametersDto.onlyWithSalary == null
        ) {
            null
        } else {
            filterParametersDto.toDomain()
        }
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
        sharedPref.edit { putString(FILTER_PARAMETERS, json) }
    }
}