package com.example.habits.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habits.models.Habit

@Database(entities = [Habit::class], version = 1)
abstract class HabitsDatabase : RoomDatabase() {
    abstract fun habitDao() : HabitDao
}