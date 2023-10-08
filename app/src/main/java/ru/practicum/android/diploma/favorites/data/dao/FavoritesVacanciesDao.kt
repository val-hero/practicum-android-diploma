package ru.practicum.android.diploma.favorites.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.favorites.data.entity.FavoriteVacancyEntity

@Dao
interface FavoritesVacanciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVacancy(vacancy: FavoriteVacancyEntity)

    @Query("DELETE FROM favorites_vacancy_table WHERE id = :id")
    suspend fun deleteVacancy(id: Long)

    @Query("SELECT * FROM favorites_vacancy_table ORDER BY saveDate DESC;")
    suspend fun getFavoritesVacancies(): List<FavoriteVacancyEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites_vacancy_table WHERE id = :id)")
    suspend fun isFavorite(id: Long): Boolean

}