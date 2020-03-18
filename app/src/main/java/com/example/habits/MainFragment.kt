package com.example.habits

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habits.habit.Habit
import com.example.habits.habit.HabitAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment: Fragment() {
    private var callback: ClickCallback? = null
    private val habits: MutableList<Habit> = mutableListOf()
    private var adapter: HabitAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as ClickCallback
        adapter = HabitAdapter(habits, callback!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        button.setOnClickListener {
            callback?.onButtonClicked()
        }
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        return view
    }
}

interface ClickCallback {
    fun onButtonClicked()
    fun onItemClicked(position: Int, habit: Habit)
}
