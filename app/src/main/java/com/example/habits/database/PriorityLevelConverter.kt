package com.example.habits.database

import androidx.room.TypeConverter
import com.example.habits.models.PriorityLevel

class PriorityLevelConverter {
    @TypeConverter
    fun fromPriorityLevel(priorityLevel: PriorityLevel) : String = priorityLevel.priority.toString()

    @TypeConverter
    fun toPriorityLevel(priorityLevelString: String) : PriorityLevel = PriorityLevel(priorityLevelString.toInt())
}
