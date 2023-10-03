package ru.practicum.android.diploma.search.data.network

import ru.practicum.android.diploma.search.domain.models.Vacancy

data class FiltredVacanciesResponse(private val result: List<Vacancy>): Response()