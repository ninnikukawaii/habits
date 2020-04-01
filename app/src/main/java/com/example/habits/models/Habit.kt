package com.example.habits.models

import java.io.Serializable

class Habit(
    var name: String,
    var description: String,
    var priority: PriorityLevel,
    var type: HabitType,
    var period: Int,
    var amount: Int
) : Serializable {

    var id: Int? = null

    companion object {
        const val HABIT = "HABIT"
    }
}