package ru.practicum.android.diploma.search.data.network.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.filter.data.network.dto.fields.CountryDto
import ru.practicum.android.diploma.filter.data.network.dto.fields.IndustryDto
import ru.practicum.android.diploma.search.data.network.VacanciesResponse
import ru.practicum.android.diploma.search.data.network.dto.VacancyDetailsDto
import ru.practicum.android.diploma.search.data.network.dto.fields.AreaDto

interface HeadHunterApiService {

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        "HH-User-Agent: WorkHub (valhero@yandex.ru)"
    )

    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancy(@Path("vacancy_id") id: String): VacancyDetailsDto

    @GET("/vacancies/{vacancy_id}/similar_vacancies")
    suspend fun getSimilarVacancies(@Path("vacancy_id") id: String): VacanciesResponse


    @GET("/areas/countries")
    suspend fun getCountries(): List<CountryDto>

    @GET("/areas")
    suspend fun getAreas(): List<AreaDto>

    @GET("/industries")
    suspend fun getIndustries(): List<IndustryDto>

    @GET("/vacancies")
    suspend fun getVacancies(
        @Query("text") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): VacanciesResponse

    @GET("/vacancies")
    suspend fun getVacanciesWithFilters(@QueryMap filters: Map<String, String>): VacanciesResponse
}