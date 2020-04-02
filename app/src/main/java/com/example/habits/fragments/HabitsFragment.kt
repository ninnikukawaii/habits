package com.example.habits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habits.R
import com.example.habits.models.Habit
import com.example.habits.models.HabitLists
import com.example.habits.models.HabitType
import com.example.habits.view_models.HabitsViewModel
import com.example.habits.views.HabitAdapter
import kotlinx.android.synthetic.main.habits_fragment.*

class HabitsFragment : Fragment() {

    private lateinit var habitType: HabitType
    private val viewModel: HabitsViewModel by activityViewModels()

    companion object {
        private const val HABIT_TYPE = "HABIT_TYPE"

        @JvmStatic
        fun newInstance(habitType: HabitType) =
            HabitsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(HABIT_TYPE, habitType)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            habitType = (it.getSerializable(HABIT_TYPE) as HabitType)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.habits_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.adapter =
            HabitAdapter (
                { habit ->
                    val bundle = Bundle()
                        .apply {
                            putSerializable(Habit.HABIT, habit)
                            putString(
                                EditFragment.TYPE,
                                EditFragment.Action.EDIT.name
                            )
                        }
                    findNavController().navigate(
                        R.id.action_nav_home_to_editHabitFragment,
                        bundle
                    )
                }, { habit ->
                    HabitLists.instance.delete(habit)
                }
            )

        viewModel.getList(habitType).observe(viewLifecycleOwner, Observer { habits ->
            (list.adapter as HabitAdapter).habits = habits
            (list.adapter as HabitAdapter).notifyDataSetChanged()
        })

        val dividerItemDecoration =
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        list.addItemDecoration(dividerItemDecoration)
    }
}
