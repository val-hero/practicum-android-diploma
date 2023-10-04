package ru.practicum.android.diploma.search.data.network.dto

import com.google.gson.annotations.SerializedName

data class VacanciesDto(
    @SerializedName("item")
    val vacancies: List<VacancyDto>,
    val found: Long
)