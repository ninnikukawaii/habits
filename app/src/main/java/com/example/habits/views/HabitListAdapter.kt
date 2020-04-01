package com.example.habits.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.habits.fragments.HabitsFragment
import com.example.habits.models.HabitType


class HabitListAdapter(fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager) {

    override fun getPageTitle(position: Int): CharSequence? = when(position) {
        0 -> HabitType.Good.name
        else -> HabitType.Bad.name
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> HabitsFragment.newInstance(HabitType.Good)
        else -> HabitsFragment.newInstance(HabitType.Bad)
    }
}
