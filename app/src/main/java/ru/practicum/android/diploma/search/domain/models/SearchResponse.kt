package ru.practicum.android.diploma.search.domain.models

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.search.data.network.api.ApiResponse


data class SearchResponse(
    val found: Int,
    @SerializedName("items")
    val vacancies: List<Vacancy>,
    val page: Int,
    val pages: Int,
    @SerializedName("per_page")
    val perPage: Int,
) : ApiResponse()



