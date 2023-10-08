package ru.practicum.android.diploma.search.data.network.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.search.data.network.VacanciesResponse
import ru.practicum.android.diploma.search.data.network.VacancyResponse
import ru.practicum.android.diploma.filter.data.network.dto.feilds.CountryDto
import ru.practicum.android.diploma.filter.data.network.dto.feilds.IndustryDto

interface RetrofitApi {

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        "HH-User-Agent: WorkHub (valhero@yandex.ru)"
    )

    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancy(@Path("vacancy_id") id: String): VacancyResponse

    @GET("/vacancies/{vacancy_id}/similar_vacancies")
    suspend fun getSimilarVacancies(@Path("vacancy_id") id: String): VacanciesResponse


    @GET("/areas")
    suspend fun getCountries(): List<CountryDto>
    @GET("/industries")
    suspend fun getIndustries() : List<IndustryDto>
    suspend fun getVacancies(@Query("text") text: String): VacanciesResponse
    @GET("/vacancies")
    suspend fun getVacanciesWithFilter(@QueryMap filters:Map<String, String>): VacanciesResponse
}