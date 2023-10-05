package ru.practicum.android.diploma.core.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.favorites.data.impl.FavoritesRepositoryImpl
import ru.practicum.android.diploma.favorites.data.entity.Converter

val favoritesModule = module {

    singleOf(::FavoritesRepositoryImpl).bind<FavoritesRepository>()

    factoryOf(::Converter)

}
