package com.example.habits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.habits.MainActivity
import com.example.habits.R
import com.example.habits.habit.Habit
import com.example.habits.habit.HabitType
import com.example.habits.habit.PriorityLevel
import kotlinx.android.synthetic.main.edit_fragment.*

class EditFragment : Fragment() {

    private lateinit var actionString: String
    private lateinit var name: String
    private lateinit var description: String
    private lateinit var type: String
    private var priority: Int? = null
    private var period: Int? = null
    private var amount: Int? = null

    companion object {
        const val TYPE = "TYPE"
    }

    enum class Action {
        ADD,
        EDIT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            actionString = it.getString(TYPE, "")
            name = it.getString(Habit.NAME, "")
            description = it.getString(Habit.DESCRIPTION, "")
            priority = it.getInt(Habit.PRIORITY, 1)
            type = it.getString(Habit.TYPE, "")
            period = it.getInt(Habit.PERIOD, -1)
            amount = it.getInt(Habit.AMOUNT, -1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val enumActionType = try {
            Action.valueOf(actionString)
        } catch (e: IllegalArgumentException) {
            alertIncorrectData("Unknown action type '$actionString'")
            findNavController().popBackStack()
            null
        }

        initialize()

        saveButton.setOnClickListener {
            if (activity is MainActivity && enumActionType != null) {
                validate(enumActionType, activity as MainActivity)
            }
        }
    }

    private fun initialize() {
        nameInput.setText(name)
        descriptionInput.setText(description)
        val position = priority ?: 1
        spinner.setSelection(position - 1)

        when (type) {
            HabitType.Good.name -> good.isChecked = true
            HabitType.Bad.name -> bad.isChecked = true
        }

        val periodSting = if (period != null && period == -1) "" else period.toString()
        periodInput.setText(periodSting)
        val amountString = if (amount != null && amount == -1) "" else amount.toString()
        amountInput.setText(amountString)
    }

    private fun validate(action: Action, activity: MainActivity) {
        val habitName = if (nameInput.text.toString() != "")
            nameInput.text.toString()
        else {
            alertIncorrectData("Specify habit name")
            return
        }

        val habitDescription = if (descriptionInput.text.toString() != "")
            descriptionInput.text.toString()
        else {
            alertIncorrectData("Specify habit description")
            return
        }

        val habitPriority = try {
            PriorityLevel(spinner.selectedItem.toString().toInt())
        } catch (e: IllegalArgumentException) {
            alertIncorrectData("Incorrect priority")
            return
        }

        val habitType = when(radio.checkedRadioButtonId) {
            good.id -> HabitType.Good
            bad.id -> HabitType.Bad
            else -> {
                alertIncorrectData("Select habit type")
                return
            }
        }

        val habitPeriod = if (periodInput.text.toString() != "")
            periodInput.text.toString().toInt()
        else {
            alertIncorrectData("Incorrect period")
            return
        }

        if (habitPeriod <= 0) {
            alertIncorrectData("Incorrect period")
            return
        }

        val habitAmount = if (amountInput.text.toString() != "")
            amountInput.text.toString().toInt()
        else {
            alertIncorrectData("Incorrect amount")
            return
        }

        if (habitAmount <= 0) {
            alertIncorrectData("Incorrect amount")
            return
        }

        when (action) {
            Action.ADD -> activity.addHabit(
                Habit(habitName, habitDescription, habitPriority, habitType, habitPeriod, habitAmount)
            )
            Action.EDIT -> {
                val position = arguments?.getInt(MainActivity.POSITION, -1)
                if (position != null && position != -1) {
                    activity.editHabit(
                        Habit(habitName, habitDescription, habitPriority, habitType, habitPeriod, habitAmount),
                        position
                    )
                }
            }
        }

        findNavController().popBackStack()
    }

    private fun alertIncorrectData(message: String) {
        Toast
            .makeText(context, message, Toast.LENGTH_LONG)
            .show()
    }
}
