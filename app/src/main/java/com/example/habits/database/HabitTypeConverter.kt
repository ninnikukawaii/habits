package com.example.habits.database

import androidx.room.TypeConverter
import com.example.habits.models.HabitType

class HabitTypeConverter {
    @TypeConverter
    fun fromHabitType(habitType: HabitType) : String = habitType.name

    @TypeConverter
    fun toHabitType(habitTypeName: String) : HabitType = HabitType.valueOf(habitTypeName)
}
