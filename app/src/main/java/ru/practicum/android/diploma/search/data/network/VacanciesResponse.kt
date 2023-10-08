package ru.practicum.android.diploma.search.data.network

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.search.data.network.api.ApiResponse
import ru.practicum.android.diploma.search.data.network.dto.VacancyDto

data class VacanciesResponse(
    val found: Int,
    @SerializedName("items")
    val vacancies: List<VacancyDto>,
    val page: Int,
    val pages: Int,
    @SerializedName("per_page")
    val perPage: Int,
) : ApiResponse()