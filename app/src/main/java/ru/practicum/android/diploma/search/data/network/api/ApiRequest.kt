package ru.practicum.android.diploma.search.data.network.api

sealed interface ApiRequest {
    data class VacancySearchRequest(
        val query: String,
        val page: Int,
        val filters: HashMap<String, String>
    ) : ApiRequest

    data class SimilarVacancySearchRequest(val id: String) : ApiRequest

    data class VacancyDetailsRequest(val id: String) : ApiRequest
}
