package com.example.habits.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.habits.models.Habit

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit")
    fun getAll() : LiveData<List<Habit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg habit: Habit)

    @Update
    fun update(habit: Habit)

    @Delete
    fun delete(habit: Habit)
}