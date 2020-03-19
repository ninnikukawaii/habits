package com.example.habits.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.MainActivity
import com.example.habits.R
import com.example.habits.habit.Habit
import com.example.habits.habit.HabitAdapter
import com.example.habits.habit.HabitListAdapter
import com.example.habits.habit.HabitType
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

        viewPager.adapter = HabitListAdapter(childFragmentManager,
            object : SetRecycleView {
                override fun set(context: Context, recyclerView: RecyclerView, habitType: HabitType) {
                    val viewHabits = (activity as MainActivity).getList(habitType)

                    recyclerView.adapter = HabitAdapter(viewHabits) { position: Int, habit ->
                        val bundle = Bundle()
                            .apply {
                                putString(Habit.NAME, habit.name)
                                putString(Habit.DESCRIPTION, habit.description)
                                putInt(Habit.PRIORITY, habit.priority.priority)
                                putString(Habit.TYPE, habit.type.name)
                                putInt(Habit.PERIOD, habit.period)
                                putInt(Habit.AMOUNT, habit.amount)
                                putInt(MainActivity.POSITION, position)
                                putString(EditFragment.TYPE, EditFragment.Action.EDIT.name)
                            }
                        findNavController().navigate(R.id.action_nav_home_to_editHabitFragment, bundle)
                    }

                    val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                    recyclerView.addItemDecoration(dividerItemDecoration)
                }
            })

        tabLayout.setupWithViewPager(viewPager)
    }
}
