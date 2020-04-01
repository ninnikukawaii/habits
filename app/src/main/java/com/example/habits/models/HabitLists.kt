package com.example.habits.models

class HabitLists private constructor() {
    private object HOLDER {
        val INSTANCE = HabitLists()
    }

    companion object {
        val INSTANCE: HabitLists by lazy { HOLDER.INSTANCE }
    }

    private var currentId = 0
    private val listHabits = mutableListOf<Habit>()

    fun add(habit: Habit) {
        habit.id = currentId++
        listHabits.add(habit)
    }

    fun edit(habit: Habit) {
        val position = findHabitPosition(habit.id)
        if (position != -1) {
            listHabits[position] = habit
        }
    }

    fun getList(type: HabitType): List<Habit>
            = listHabits.filter { habit -> habit.type == type }

    private fun findHabitPosition(id: Int?) : Int {
        if (id == null) return -1

        for (i in 0..listHabits.size) {
            if (listHabits[i].id == id) {
                return i
            }
        }
        return -1
    }
}
