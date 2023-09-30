package ru.practicum.android.diploma.search.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RetrofitApi {

    @Headers(
        "Authorization: Bearer YOUR_TOKEN",
        "HH-User-Agent: Application Name (valhero@yandex.ru)"
    )

    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancy(@Path("vacancy_id") id: String): Response

    @GET("/vacancies/{vacancy_id}/similar_vacancies")
    suspend fun getSimilarVacancies(@Path("vacancy_id") id: String): Response

    @GET("/vacancies")
    suspend fun getVacanciesWithFilter(@QueryMap filters:Map<String, String>): Response
}