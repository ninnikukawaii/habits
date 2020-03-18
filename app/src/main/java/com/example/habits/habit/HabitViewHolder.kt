package com.example.habits.habit

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.R
import kotlinx.android.extensions.LayoutContainer

class HabitViewHolder(
    override val containerView: View, private val event: (position: Int, habit: Habit) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val nameText = containerView.findViewById<TextView>(R.id.name)
    private val descriptionText = containerView.findViewById<TextView>(R.id.description)
    private val priorityText = containerView.findViewById<TextView>(R.id.priority)
    private val typeText = containerView.findViewById<TextView>(R.id.type)
    private val periodText = containerView.findViewById<TextView>(R.id.period)
    private val layout = containerView.findViewById<ConstraintLayout>(R.id.habit)

    fun bind(habit: Habit) {
        nameText.text = habit.name
        descriptionText.text = habit.description
        priorityText.text = habit.priority.priority.toString()
        typeText.text = habit.type.name
        periodText.text = "${habit.amount} times every ${habit.period} days"

        layout.setOnClickListener {
            event(adapterPosition, habit)
        }
    }
}