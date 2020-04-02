package com.example.habits.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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

    private val goodObserver: Observer<List<Habit>>
    private val badObserver: Observer<List<Habit>>

    init {
        goodObserver = Observer { list ->
            goodHabitsForShow.value = list
        }
        HabitLists.instance.getList(HabitType.Good).observeForever(goodObserver)
        badObserver = Observer { list ->
            badHabitsForShow.value = list
        }
        HabitLists.instance.getList(HabitType.Bad).observeForever(badObserver)
    }

    fun getList(type: HabitType): MutableLiveData<List<Habit>> = when (type) {
        HabitType.Good -> goodHabitsForShow
        HabitType.Bad -> badHabitsForShow
    }

    private fun filterByTitle(containedSubstring: String, habitType: HabitType) : List<Habit>
            = HabitLists.instance.getList(habitType).value
        ?.filter { habit ->
            habit.name.toLowerCase(Locale.ROOT).contains(containedSubstring.toLowerCase(Locale.ROOT))
        } ?: emptyList()

    override fun onCleared() {
        super.onCleared()
        HabitLists.instance.getList(HabitType.Good).removeObserver(goodObserver)
        HabitLists.instance.getList(HabitType.Good).removeObserver(badObserver)
    }
}