package ru.practicum.android.diploma.core.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.favorites.data.impl.FavoritesRepositoryImpl
import ru.practicum.android.diploma.favorites.data.entity.Converter
import ru.practicum.android.diploma.favorites.domain.usecase.AddToFavorites
import ru.practicum.android.diploma.favorites.domain.usecase.DeleteFromFavorites
import ru.practicum.android.diploma.favorites.domain.usecase.GetFavorites
import ru.practicum.android.diploma.favorites.domain.usecase.IsInFavoritesCheck

val favoritesModule = module {

    singleOf(::FavoritesRepositoryImpl).bind<FavoritesRepository>()

    factoryOf(::Converter)

    factoryOf(::AddToFavorites).bind<AddToFavorites>()

    factoryOf(::DeleteFromFavorites).bind<DeleteFromFavorites>()

    factoryOf(::GetFavorites).bind<GetFavorites>()

    factoryOf(::IsInFavoritesCheck).bind<IsInFavoritesCheck>()

}
