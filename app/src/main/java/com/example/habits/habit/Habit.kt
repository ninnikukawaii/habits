package com.example.habits.habit

class Habit(
    var name: String,
    var description: String,
    var priority: PriorityLevel,
    var type: HabitType,
    var period: Int,
    var amount: Int
) {
    companion object {
        const val NAME = "NAME"
        const val DESCRIPTION = "DESCRIPTION"
        const val PRIORITY = "PRIORITY"
        const val TYPE = "TYPE"
        const val PERIOD = "PERIOD"
        const val AMOUNT = "AMOUNT"
    }
}