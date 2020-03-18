package com.example.habits

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.habits.habit.Habit
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    val habits = mutableListOf<Habit>()
    private lateinit var appBarConfiguration: AppBarConfiguration

    fun addHabit(habit: Habit) = habits.add(habit)
    fun editHabit(habit: Habit, position: Int) {
        habits[position] = habit
    }

    companion object {
        const val ADD = 1
        const val EDIT = 2
        const val POSITION = "POSITION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

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
