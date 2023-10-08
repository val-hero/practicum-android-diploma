package ru.practicum.android.diploma.core.di

import android.content.Context
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.core.utils.Constants.FILTER_PARAMETERS
import ru.practicum.android.diploma.filter.data.impl.AreasRepositoryImpl
import ru.practicum.android.diploma.filter.data.impl.CountryRepositoryImpl
import ru.practicum.android.diploma.filter.data.impl.IndustryRepositoryImpl
import ru.practicum.android.diploma.filter.data.storage.FilterStorageImpl
import ru.practicum.android.diploma.filter.domain.AreasRepository
import ru.practicum.android.diploma.filter.domain.CountryRepository
import ru.practicum.android.diploma.filter.domain.IndustryRepository
import ru.practicum.android.diploma.filter.domain.api.FilterStorage
import ru.practicum.android.diploma.filter.domain.usecase.GetAreasUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetCountriesUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetIndustriesUseCase
import ru.practicum.android.diploma.filter.ui.SelectCountry.viewmodel.SelectCountryViewModel
import ru.practicum.android.diploma.filter.ui.selectindustry.viewmodel.SelectIndustryViewModel
import ru.practicum.android.diploma.filter.ui.selectregion.viewmodel.SelectRegionViewModel

val filterModule = module {

    factory { Gson() }

    single {
        androidContext()
            .getSharedPreferences(FILTER_PARAMETERS, Context.MODE_PRIVATE)
    }

    singleOf(::FilterStorageImpl).bind<FilterStorage>()

    singleOf(::CountryRepositoryImpl).bind<CountryRepository>()

    factoryOf(::GetCountriesUseCase).bind<GetCountriesUseCase>()

    viewModelOf(::SelectCountryViewModel).bind<SelectCountryViewModel>()

    singleOf(::AreasRepositoryImpl).bind<AreasRepository>()

    factoryOf(::GetAreasUseCase).bind<GetAreasUseCase>()

    viewModelOf(::SelectRegionViewModel).bind()

    singleOf(::IndustryRepositoryImpl).bind<IndustryRepository>()

    factoryOf(::GetIndustriesUseCase).bind<GetIndustriesUseCase>()

    viewModelOf(::SelectIndustryViewModel).bind()
}

