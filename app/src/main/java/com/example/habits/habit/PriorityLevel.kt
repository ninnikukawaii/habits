package com.example.habits.habit

class PriorityLevel(value: Int) {
    companion object Constraints {
        private const val minValue = 1
        private const val maxValue = 5
    }

    val priority: Int

    init {
        if (value in minValue..maxValue)
            priority = value
        else
            throw IllegalArgumentException()
    }
}