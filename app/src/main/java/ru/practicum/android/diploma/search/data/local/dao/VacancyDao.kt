package ru.practicum.android.diploma.search.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.favorites.data.entity.FavoritesVacanciesEntity
import ru.practicum.android.diploma.search.data.local.entity.VacancyEntity

@Dao
interface FavoritesVacanciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVacancy(track: FavoritesVacanciesEntity)

    @Query("DELETE FROM favorites_vacancy_table WHERE id = :id")
    suspend fun deleteVacancy(id: Long)

    @Query("SELECT * FROM favorites_vacancy_table ORDER BY saveDate DESC;")
    suspend fun getFavoritesVacancies(): List<FavoritesVacanciesEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites_vacancy_table WHERE id = :id)")
    suspend fun isFavorite(id: Long): Boolean
}