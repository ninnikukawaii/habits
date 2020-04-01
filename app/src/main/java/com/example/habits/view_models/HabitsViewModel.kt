package com.example.habits.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habits.models.Habit
import com.example.habits.models.HabitLists
import com.example.habits.models.HabitType
import java.util.*

class HabitsViewModel : ViewModel() {
    private val goodHabitsForShow = MutableLiveData<List<Habit>>()
    private val badHabitsForShow = MutableLiveData<List<Habit>>()
    var filterSubstring: String = ""
        set(value) {
            goodHabitsForShow.value = filterByTitle(value, HabitType.Good)
            badHabitsForShow.value = filterByTitle(value, HabitType.Bad)
        }

    init {
        goodHabitsForShow.value = HabitLists.INSTANCE.getList(HabitType.Good)
        badHabitsForShow.value = HabitLists.INSTANCE.getList(HabitType.Bad)
    }

    fun getList(type: HabitType): MutableLiveData<List<Habit>> = when (type) {
        HabitType.Good -> goodHabitsForShow
        HabitType.Bad -> badHabitsForShow
    }

    private fun filterByTitle(containedSubstring: String, habitType: HabitType) : List<Habit>
            = HabitLists.INSTANCE.getList(habitType).filter { habit ->
            habit.name.toLowerCase(Locale.ROOT).contains(containedSubstring.toLowerCase(Locale.ROOT))
    }
}