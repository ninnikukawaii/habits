package com.example.habits.habit

class Habit(
    var name: String,
    var description: String,
    var priority: PriorityLevel,
    var type: HabitType,
    var period: Int,
    var amount: Int
)