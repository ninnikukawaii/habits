package com.example.habits.habit

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.habits.fragments.HabitsFragment
import com.example.habits.fragments.SetRecycleView


class HabitListAdapter(fragmentManager: FragmentManager, private val setRecycleView: SetRecycleView)
    : FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> HabitsFragment.newInstance(setRecycleView, HabitType.Good)
        else -> HabitsFragment.newInstance(setRecycleView, HabitType.Bad)
    }

    override fun getPageTitle(position: Int): CharSequence? = when(position) {
        0 -> HabitType.Good.name
        else -> HabitType.Bad.name
    }
}
