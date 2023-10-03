package ru.practicum.android.diploma.search.data.network

import ru.practicum.android.diploma.search.domain.models.Vacancy

data class VacancyResponce(private val result: Vacancy): Response()