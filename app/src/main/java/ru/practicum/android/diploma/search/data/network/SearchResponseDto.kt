package ru.practicum.android.diploma.search.data.network

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.search.data.network.api.ApiResponse
import ru.practicum.android.diploma.search.data.network.dto.VacancyDto
import ru.practicum.android.diploma.search.data.network.dto.toDomain
import ru.practicum.android.diploma.search.domain.models.SearchResponse

data class SearchResponseDto(
    val found: Int,
    @SerializedName("items")
    val vacancies: List<VacancyDto>,
    val page: Int,
    val pages: Int,
    @SerializedName("per_page")
    val perPage: Int,
) : ApiResponse()

fun SearchResponseDto.toDomain():SearchResponse{
    return SearchResponse(
        found=this.found,
        vacancies = this.vacancies.map { it.toDomain() },
        page = this.page,
        pages = this.pages,
        perPage = this.perPage
    )
}