package ru.practicum.android.diploma.core.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.filter.data.impl.CountryRepositoryImpl
import ru.practicum.android.diploma.filter.data.impl.IndustryRepositoryImpl
import ru.practicum.android.diploma.filter.domain.CountryRepository
import ru.practicum.android.diploma.filter.domain.IndustryRepository
import ru.practicum.android.diploma.filter.domain.usecase.GetCountriesUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetIndustriesUseCase
import ru.practicum.android.diploma.filter.ui.SelectCountry.viewmodel.SelectCountryViewModel
import ru.practicum.android.diploma.filter.ui.selectindustry.viewmodel.SelectIndustryViewModel

val filterModule = module {

    singleOf(::CountryRepositoryImpl).bind<CountryRepository>()

    factoryOf(::GetCountriesUseCase).bind<GetCountriesUseCase>()

    viewModelOf(::SelectCountryViewModel).bind<SelectCountryViewModel>()

    singleOf(::IndustryRepositoryImpl).bind<IndustryRepository>()

    factoryOf(::GetIndustriesUseCase).bind<GetIndustriesUseCase>()

    viewModelOf(::SelectIndustryViewModel).bind()
}