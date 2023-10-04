package ru.practicum.android.diploma.core.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.search.data.SearchRepositoryImpl
import ru.practicum.android.diploma.search.data.network.api.RetrofitApi

const val BASE_URL = "https://api.hh.ru/"

val searchModule = module {

    single<RetrofitApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }

    singleOf(::SearchRepositoryImpl).bind<SearchRepositoryImpl>()


}
