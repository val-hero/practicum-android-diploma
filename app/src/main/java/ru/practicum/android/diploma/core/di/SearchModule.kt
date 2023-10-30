package ru.practicum.android.diploma.core.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.search.data.SearchRepositoryImpl
import ru.practicum.android.diploma.search.data.network.api.HeadHunterApiService
import ru.practicum.android.diploma.search.data.network.client.NetworkClient
import ru.practicum.android.diploma.search.data.network.client.NetworkClientImpl
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.usecase.GetSimilarVacanciesUseCase
import ru.practicum.android.diploma.search.domain.usecase.GetVacancyDetailsUseCase
import ru.practicum.android.diploma.search.domain.usecase.SearchUseCase
import ru.practicum.android.diploma.search.ui.viewmodel.SearchViewModel

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

    viewModelOf(::SearchViewModel).bind<SearchViewModel>()

    singleOf(::SearchRepositoryImpl).bind<SearchRepository>()

    factoryOf(::SearchUseCase).bind<SearchUseCase>()

    factoryOf(::GetVacancyDetailsUseCase).bind<GetVacancyDetailsUseCase>()

    factoryOf(::GetSimilarVacanciesUseCase).bind<GetSimilarVacanciesUseCase>()

}
