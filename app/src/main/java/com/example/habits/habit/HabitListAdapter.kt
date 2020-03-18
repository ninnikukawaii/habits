package com.example.habits.habit

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.habits.fragments.HabitsFragment


class HabitListAdapter(activity: FragmentActivity, private val habits: List<Habit>) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> HabitsFragment.newInstance()
        else -> HabitsFragment.newInstance()
    }

}
