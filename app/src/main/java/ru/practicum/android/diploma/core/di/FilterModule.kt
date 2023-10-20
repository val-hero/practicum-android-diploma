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
import ru.practicum.android.diploma.filter.ui.filtration_settings.viewmodel.FilteringSettingsViewModel
import ru.practicum.android.diploma.filter.data.impl.AreasRepositoryImpl
import ru.practicum.android.diploma.filter.data.impl.CountryRepositoryImpl
import ru.practicum.android.diploma.filter.data.impl.IndustryRepositoryImpl
import ru.practicum.android.diploma.filter.data.storage.FilterStorageImpl
import ru.practicum.android.diploma.filter.domain.AreasRepository
import ru.practicum.android.diploma.filter.domain.CountryRepository
import ru.practicum.android.diploma.filter.domain.IndustryRepository
import ru.practicum.android.diploma.filter.domain.api.FilterStorage
import ru.practicum.android.diploma.filter.domain.usecase.ClearFilterSettingsUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetAreasInCountryUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetAreasUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetCountriesUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetFilterSettingsUseCase
import ru.practicum.android.diploma.filter.domain.usecase.GetIndustriesUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveAreaUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveCountryUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveIndustryUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveSalaryFlagUseCase
import ru.practicum.android.diploma.filter.domain.usecase.SaveSalaryUseCase
import ru.practicum.android.diploma.filter.ui.select_country.viewmodel.SelectCountryViewModel
import ru.practicum.android.diploma.filter.ui.select_workplace.viewmodel.SelectWorkplaceViewModel
import ru.practicum.android.diploma.filter.ui.select_industry.viewmodel.SelectIndustryViewModel
import ru.practicum.android.diploma.filter.ui.select_region.viewmodel.SelectRegionViewModel

val filterModule = module {

    factory { Gson() }

    single {
        androidContext()
            .getSharedPreferences(FILTER_PARAMETERS, Context.MODE_PRIVATE)
    }

    singleOf(::FilterStorageImpl).bind<FilterStorage>()

    factoryOf(::GetFilterSettingsUseCase).bind<GetFilterSettingsUseCase>()

    factoryOf(::SaveAreaUseCase).bind<SaveAreaUseCase>()

    factoryOf(::SaveCountryUseCase).bind<SaveCountryUseCase>()

    factoryOf(::SaveIndustryUseCase).bind<SaveIndustryUseCase>()

    factoryOf(::SaveSalaryUseCase).bind<SaveSalaryUseCase>()

    factoryOf(::SaveSalaryFlagUseCase).bind<SaveSalaryFlagUseCase>()

    factoryOf(::ClearFilterSettingsUseCase).bind<ClearFilterSettingsUseCase>()

    singleOf(::CountryRepositoryImpl).bind<CountryRepository>()

    factoryOf(::GetCountriesUseCase).bind<GetCountriesUseCase>()

    viewModelOf(::SelectCountryViewModel).bind<SelectCountryViewModel>()

    viewModelOf(::FilteringSettingsViewModel).bind<FilteringSettingsViewModel>()

    viewModelOf(::SelectWorkplaceViewModel).bind<SelectWorkplaceViewModel>()

    singleOf(::AreasRepositoryImpl).bind<AreasRepository>()

    factoryOf(::GetAreasUseCase).bind<GetAreasUseCase>()

    factoryOf(::GetAreasInCountryUseCase).bind<GetAreasInCountryUseCase>()

    viewModelOf(::SelectRegionViewModel).bind()

    singleOf(::IndustryRepositoryImpl).bind<IndustryRepository>()

    factoryOf(::GetIndustriesUseCase).bind<GetIndustriesUseCase>()

    viewModelOf(::SelectIndustryViewModel).bind()
}

