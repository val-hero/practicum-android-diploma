package ru.practicum.android.diploma.favorites.data.entity

import ru.practicum.android.diploma.search.domain.models.Vacancy
import java.util.Calendar

class Converter {

    fun mapToVacancy(vacancy: FavoritesVacanciesEntity): Vacancy {
        return Vacancy(
            vacancy.area,
            vacancy.contacts,
            vacancy.description,
            vacancy.employer,
            vacancy.employment,
            vacancy.experience,
            vacancy.id,
            vacancy.keySkills,
            vacancy.name,
            vacancy.salary,
            vacancy.schedule,
            vacancy.type
        )
    }

    fun mapToEntity(vacancy: Vacancy): FavoritesVacanciesEntity {
        return FavoritesVacanciesEntity (
            vacancy.area,
            vacancy.contacts,
            vacancy.description,
            vacancy.employer,
            vacancy.employment,
            vacancy.experience,
            vacancy.id,
            vacancy.keySkills,
            vacancy.name,
            vacancy.salary,
            vacancy.schedule,
            vacancy.type,
            Calendar.getInstance().timeInMillis
        )
    }
}