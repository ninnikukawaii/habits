package com.example.habits.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.habits.database.HabitTypeConverter
import com.example.habits.database.PriorityLevelConverter
import java.io.Serializable

@Entity
@TypeConverters(HabitTypeConverter::class, PriorityLevelConverter::class)
class Habit(
    var name: String,
    var description: String,
    @TypeConverters(PriorityLevelConverter::class) var priority: PriorityLevel,
    @TypeConverters(HabitTypeConverter::class) var type: HabitType,
    var period: Int,
    var amount: Int
) : Serializable {

    @PrimaryKey(autoGenerate = true) var id: Int? = null

    companion object {
        const val HABIT = "HABIT"
    }
}