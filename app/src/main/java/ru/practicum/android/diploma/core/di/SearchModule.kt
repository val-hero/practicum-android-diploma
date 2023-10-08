package ru.practicum.android.diploma.core.di

import org.koin.android.ext.koin.androidContext

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.filter.data.impl.CountryRepositoryImpl
import ru.practicum.android.diploma.filter.domain.CountryRepository
import ru.practicum.android.diploma.filter.domain.usecase.GetCountriesUseCase
import ru.practicum.android.diploma.filter.ui.SelectCountry.viewmodel.SelectCountryViewModel
import ru.practicum.android.diploma.search.data.SearchRepositoryImpl
import ru.practicum.android.diploma.search.data.network.api.HeadHunterApiService
import ru.practicum.android.diploma.search.data.network.client.NetworkClient
import ru.practicum.android.diploma.search.data.network.client.NetworkClientImpl
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.usecase.GetVacancyDetailsUseCase
import ru.practicum.android.diploma.search.domain.usecase.SearchUseCase
import ru.practicum.android.diploma.search.domain.usecase.SearchWithFiltersUseCase
import ru.practicum.android.diploma.search.ui.SearchViewModel

const val BASE_URL = "https://api.hh.ru/"

val searchModule = module {

    single<HeadHunterApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeadHunterApiService::class.java)
    }

    single<NetworkClient> {
        NetworkClientImpl(androidContext(), api = get())
    }

    singleOf(::SearchRepositoryImpl).bind<SearchRepository>()

    viewModelOf(::SearchViewModel).bind<SearchViewModel>()

    factoryOf(::SearchUseCase).bind<SearchUseCase>()

    factoryOf(::SearchWithFiltersUseCase).bind<SearchWithFiltersUseCase>()

    factoryOf(::GetVacancyDetailsUseCase).bind<GetVacancyDetailsUseCase>()
}
