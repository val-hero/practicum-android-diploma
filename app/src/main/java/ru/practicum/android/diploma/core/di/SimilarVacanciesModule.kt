package ru.practicum.android.diploma.core.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.similar_vacancies.ui.viewmodel.SimilarVacanciesViewModel

val similarVacanciesModule = module {
    viewModelOf(::SimilarVacanciesViewModel).bind<SimilarVacanciesViewModel>()
}