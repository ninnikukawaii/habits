package com.example.habits.models

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.habits.database.HabitsDatabase

class HabitLists private constructor() {
    private object HOLDER {
        val INSTANCE = HabitLists()
    }

    companion object {
        val instance: HabitLists by lazy { HOLDER.INSTANCE }
    }

    private val goodHabits = MutableLiveData<List<Habit>>()
    private val badHabits = MutableLiveData<List<Habit>>()
    private lateinit var database: HabitsDatabase

    fun add(habit: Habit) {
        database.habitDao().insert(habit)
    }

    fun edit(habit: Habit) {
        database.habitDao().update(habit)
    }

    fun delete(habit: Habit) {
        database.habitDao().delete(habit)
    }

    fun getList(type: HabitType): LiveData<List<Habit>> = when (type) {
        HabitType.Good -> goodHabits
        HabitType.Bad -> badHabits
    }

    fun initialize(applicationContext: Context, owner: LifecycleOwner) {
        database = Room.databaseBuilder(
            applicationContext,
            HabitsDatabase::class.java,
            "Habits"
        ).allowMainThreadQueries().build()
        database.habitDao().getAll().observe(owner, Observer {
            goodHabits.value = it.filter { habit -> habit.type == HabitType.Good }
            badHabits.value = it.filter { habit -> habit.type == HabitType.Bad }
        })
    }
}
