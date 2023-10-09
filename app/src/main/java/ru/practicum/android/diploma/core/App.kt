package ru.practicum.android.diploma.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.practicum.android.diploma.core.di.appModule
import ru.practicum.android.diploma.core.di.favoritesModule
import ru.practicum.android.diploma.core.di.filterModule
import ru.practicum.android.diploma.core.di.searchModule
import ru.practicum.android.diploma.core.di.vacancyDetailsModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    appModule,
                    searchModule,
                    filterModule,
                    favoritesModule,
                    vacancyDetailsModule
                )
            )
        }
    }
}