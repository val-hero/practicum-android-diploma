package ru.practicum.android.diploma.core.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.data.impl.FavoritesRepositoryImpl
import ru.practicum.android.diploma.favorites.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.favorites.domain.usecase.AddToFavorites
import ru.practicum.android.diploma.favorites.domain.usecase.DeleteFromFavorites
import ru.practicum.android.diploma.favorites.domain.usecase.GetFavorites
import ru.practicum.android.diploma.favorites.domain.usecase.GetFromFavorite
import ru.practicum.android.diploma.favorites.domain.usecase.IsInFavoritesCheck
import ru.practicum.android.diploma.favorites.ui.viewmodel.FavoritesFragmentViewModel

val favoritesModule = module {

    singleOf(::FavoritesRepositoryImpl).bind<FavoritesRepository>()

    factoryOf(::AddToFavorites).bind<AddToFavorites>()

    factoryOf(::DeleteFromFavorites).bind<DeleteFromFavorites>()

    factoryOf(::GetFavorites).bind<GetFavorites>()

    factoryOf(::IsInFavoritesCheck).bind<IsInFavoritesCheck>()

    viewModelOf(::FavoritesFragmentViewModel).bind()

    factoryOf(::GetFromFavorite).bind()

}
