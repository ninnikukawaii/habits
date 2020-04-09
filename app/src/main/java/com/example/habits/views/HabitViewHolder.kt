package com.example.habits.views

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.R
import com.example.habits.models.Habit
import kotlinx.android.extensions.LayoutContainer

class HabitViewHolder(
    override val containerView: View, private val editEvent: (habit: Habit) -> Unit, private val deleteEvent: (habit: Habit) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val nameText = containerView.findViewById<TextView>(R.id.name)
    private val descriptionText = containerView.findViewById<TextView>(R.id.description)
    private val priorityText = containerView.findViewById<TextView>(R.id.priority)
    private val typeText = containerView.findViewById<TextView>(R.id.type)
    private val periodText = containerView.findViewById<TextView>(R.id.period)
    private val editButton: ImageButton = containerView.findViewById(R.id.editButton)
    private val deleteButton: ImageButton = containerView.findViewById(R.id.deleteButton)

    fun bind(habit: Habit) {
        nameText.text = habit.name
        descriptionText.text = habit.description
        priorityText.text = habit.priority.priority.toString()
        typeText.text = habit.type.name
        periodText.text = "${habit.amount} times every ${habit.period} days"

        editButton.setOnClickListener {
            editEvent(habit)
        }

        deleteButton.setOnClickListener {
            deleteEvent(habit)
        }
    }
}