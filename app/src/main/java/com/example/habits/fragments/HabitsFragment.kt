package com.example.habits.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.R
import com.example.habits.habit.HabitType
import kotlinx.android.synthetic.main.habits_fragment.*
import java.io.Serializable

class HabitsFragment : Fragment() {

    private lateinit var callbackSetRecycleView: SetRecycleView
    private lateinit var habitType: HabitType

    companion object {
        private const val HABIT_TYPE = "HABIT_TYPE"
        private const val RECYCLER_VIEW_CONFIGURE = "recyclerViewConfigure"

        @JvmStatic
        fun newInstance(setRecycleView: SetRecycleView, habitType: HabitType) =
            HabitsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(HABIT_TYPE, habitType)
                    putSerializable(RECYCLER_VIEW_CONFIGURE, setRecycleView)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            callbackSetRecycleView = (it.getSerializable(RECYCLER_VIEW_CONFIGURE) as SetRecycleView)
            habitType = (it.getSerializable(HABIT_TYPE) as HabitType)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.habits_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callbackSetRecycleView.set(context!!, list, habitType)
    }
}

interface SetRecycleView : Serializable {
    fun set(context: Context, recyclerView: RecyclerView, habitType: HabitType)
}
