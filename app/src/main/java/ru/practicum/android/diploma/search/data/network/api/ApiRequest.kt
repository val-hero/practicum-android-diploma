package ru.practicum.android.diploma.search.data.network.api

sealed interface ApiRequest {
    data class VacancySearchRequest(val parameters: Map<String,Any>) : ApiRequest

    data class SimilarVacancySearchRequest(val id: String) : ApiRequest

    data class VacancySearchWithFiltersRequest(val filters: Map<String, String>) : ApiRequest

    data class VacancyDetailsRequest(val id: String) : ApiRequest
}
