package ru.practicum.android.diploma.core.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.core.AppDatabase

val appModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database_team2.db")
            .build()
    }
}