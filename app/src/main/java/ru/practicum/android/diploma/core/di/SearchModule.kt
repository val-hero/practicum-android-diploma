package ru.practicum.android.diploma.core.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.filter.data.impl.CountryRepositoryImpl
import ru.practicum.android.diploma.filter.domain.CountryRepository
import ru.practicum.android.diploma.filter.domain.usecase.GetCountriesUseCase
import ru.practicum.android.diploma.search.data.network.RetrofitApi

const val BASE_URL = "https://api.hh.ru/"

val searchModule = module {

    single<RetrofitApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }

    single <CountryRepository>{
        CountryRepositoryImpl(get())
    }

    factory {
        GetCountriesUseCase(get())
    }
}
