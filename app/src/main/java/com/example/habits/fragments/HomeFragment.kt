package com.example.habits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.habits.MainActivity
import com.example.habits.R
import com.example.habits.habit.Habit
import com.example.habits.habit.HabitListAdapter
import com.example.habits.habit.HabitType
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply { arguments = Bundle().apply { } }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            val bundle = Bundle()
                .apply {
                    putString(EditFragment.TYPE, EditFragment.Action.ADD.name)
                }
            findNavController().navigate(R.id.action_nav_home_to_editHabitFragment, bundle)
        }

        val habits = if (activity is MainActivity) {
            (activity as MainActivity).habits
        } else {
            emptyList<Habit>()
        }

        activity?.let { activity ->
            viewPager.adapter = HabitListAdapter(activity, habits)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when(position) {
                    0 -> HabitType.Good.name
                    else -> HabitType.Bad.name
                }
            }.attach()
        }
    }
}
