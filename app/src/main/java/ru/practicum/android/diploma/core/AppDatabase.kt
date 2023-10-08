package ru.practicum.android.diploma.core

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.diploma.favorites.data.dao.FavoritesVacanciesDao
import ru.practicum.android.diploma.favorites.data.entity.FavoriteVacancyEntity

@Database(version = 1, entities = [FavoriteVacancyEntity::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritesVacanciesDao(): FavoritesVacanciesDao
}