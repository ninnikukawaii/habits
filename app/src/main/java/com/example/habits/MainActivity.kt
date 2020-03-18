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

    private val habits = mutableListOf<Habit>()
    private lateinit var appBarConfiguration: AppBarConfiguration

    companion object Consts {
        const val ADDING = 1
        const val EDIT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //setSupportActionBar(toolbar)

        //button.setOnClickListener {
        //    val startSecondActivity = Intent(
        //        this,
        //        AddingActivity::class.java
        //    )
        //    startActivityForResult(startSecondActivity, ADDING)
        //}

        //setSupportActionBar(toolbar)

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

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode != ADDING && requestCode != EDIT) || (resultCode != ADDING && resultCode != EDIT))
            return

        if (data != null) {
            val habitName = data.getStringExtra(AddingActivity.NAME)
            val habitDescription = data.getStringExtra(AddingActivity.DESCRIPTION)
            val habitPriority = PriorityLevel(data.getIntExtra(AddingActivity.PRIORITY, 0))
            val habitType = HabitType.valueOf(data.getStringExtra(AddingActivity.TYPE)!!)
            val habitPeriod = data.getIntExtra(AddingActivity.PERIOD, 0)
            val habitAmount = data.getIntExtra(AddingActivity.AMOUNT, 0)

            if (resultCode == ADDING)
                addHabit(Habit(habitName!!, habitDescription!!, habitPriority, habitType, habitPeriod, habitAmount))
            else {
                val index = data.getIntExtra(AddingActivity.INDEX, -1)
                if (index == -1)
                    return

                val editHabit = habits[index]
                editHabit.name = habitName!!
                editHabit.description = habitDescription!!
                editHabit.priority = habitPriority
                editHabit.type = habitType
                editHabit.period = habitPeriod
                editHabit.amount = habitAmount

                adapter.notifyItemChanged(index)
            }
        }
    }

    private fun addHabit(habit: Habit) {
        habits.add(habit)
        adapter.notifyDataSetChanged()
    }*/
}
