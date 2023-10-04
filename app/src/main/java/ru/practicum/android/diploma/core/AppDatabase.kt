package ru.practicum.android.diploma.core

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.diploma.favorites.data.dao.FavoritesVacanciesDao
import ru.practicum.android.diploma.favorites.data.entity.FavoritesVacanciesEntity
import ru.practicum.android.diploma.search.data.local.dao.VacancyDao
import ru.practicum.android.diploma.search.data.local.entity.VacancyEntity

@Database(version = 1, entities = [VacancyEntity::class, FavoritesVacanciesEntity::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun vacancyDao(): VacancyDao

    abstract fun favoritesVacanciesDao() : FavoritesVacanciesDao
}