package ru.practicum.android.diploma.search.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RetrofitApi {

    @Headers(
        "Authorization: Bearer YOUR_TOKEN",
        "HH-User-Agent: Application Name (valhero@yandex.ru)"
    )
    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancy(@Path("vacancy_id") id: String): Response

}