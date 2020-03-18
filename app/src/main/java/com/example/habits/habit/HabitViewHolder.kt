package com.example.habits.habit

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.ClickCallback
import com.example.habits.R

class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameText = itemView.findViewById<TextView>(R.id.name)
    private val descriptionText = itemView.findViewById<TextView>(R.id.description)
    private val priorityText = itemView.findViewById<TextView>(R.id.priority)
    private val typeText = itemView.findViewById<TextView>(R.id.type)
    private val periodText = itemView.findViewById<TextView>(R.id.period)
    private val layout = itemView.findViewById<ConstraintLayout>(R.id.habit)

    fun bind(habit: Habit, callback: ClickCallback) {
        nameText.text = habit.name
        descriptionText.text = habit.description
        priorityText.text = habit.priority.priority.toString()
        typeText.text = habit.type.name
        periodText.text = "${habit.amount} times every ${habit.period} days"

        layout.setOnClickListener {
            callback.onItemClicked(adapterPosition, habit)
        }
    }
}