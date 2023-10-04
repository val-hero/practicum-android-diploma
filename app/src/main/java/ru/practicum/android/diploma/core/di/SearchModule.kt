package ru.practicum.android.diploma.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
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
import ru.practicum.android.diploma.search.data.network.RetrofitApi
import ru.practicum.android.diploma.search.data.SearchRepositoryImpl
import ru.practicum.android.diploma.search.data.network.api.RetrofitApi
import ru.practicum.android.diploma.search.domain.usecase.GetVacancyDetailsUseCase
import ru.practicum.android.diploma.search.domain.usecase.SearchUseCase
import ru.practicum.android.diploma.search.domain.usecase.SearchWithFiltersUseCase

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

    viewModel {
        SelectCountryViewModel(get())
    }

    singleOf(::SearchRepositoryImpl).bind<SearchRepositoryImpl>()

    factoryOf(::SearchUseCase).bind<SearchUseCase>()

    factoryOf(::SearchWithFiltersUseCase).bind<SearchWithFiltersUseCase>()

    factoryOf(::GetVacancyDetailsUseCase).bind<GetVacancyDetailsUseCase>()
}
