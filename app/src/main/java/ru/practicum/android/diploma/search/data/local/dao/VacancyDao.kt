package ru.practicum.android.diploma.search.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.search.data.local.entity.VacancyEntity

@Dao
interface VacancyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vacancies: List<VacancyEntity>)

    @Query("SELECT * FROM vacancy_table")
    suspend fun getAll(): List<VacancyEntity>

    @Query("SELECT * FROM vacancy_table WHERE id = :id")
    suspend fun getById(id: Long): VacancyEntity

    @Query("DELETE FROM vacancy_table")
    suspend fun deleteAll()
}
