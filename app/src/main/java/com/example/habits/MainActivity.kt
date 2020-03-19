package com.example.habits

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.habits.habit.Habit
import com.example.habits.habit.HabitType
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val goodHabits = mutableListOf<Habit>()
    private val badHabits = mutableListOf<Habit>()
    private lateinit var appBarConfiguration: AppBarConfiguration

    fun addHabit(habit: Habit) {
        val habits = getList(habit.type)
        habits.add(habit)
    }

    fun editHabit(habit: Habit, position: Int, oldType: HabitType?) {
        if (habit.type == oldType) {
            val habits = getList(habit.type)
            habits[position] = habit
        }
        else {
            if (oldType != null) {
                val olds = getList(oldType)
                olds.removeAt(position)
                val habits = getList(habit.type)
                habits.add(habit)
            }
            else
                addHabit(habit)
        }
    }

    fun getList(habitType: HabitType): MutableList<Habit> {
        return when(habitType) {
            HabitType.Good -> goodHabits
            HabitType.Bad -> badHabits
        }
    }

    companion object {
        const val ADD = 1
        const val EDIT = 2
        const val POSITION = "POSITION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_about), navDrawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navDrawer.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
