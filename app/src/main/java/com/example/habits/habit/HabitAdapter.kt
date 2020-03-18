package com.example.habits.habit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.ClickCallback
import com.example.habits.R

class HabitAdapter(private val habits: List<Habit>, private val clickCallback: ClickCallback)
    : RecyclerView.Adapter<HabitViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HabitViewHolder(inflater.inflate(R.layout.habit_view, parent, false))
    }

    override fun getItemCount(): Int = habits.size

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(habits[position], clickCallback)
    }
}