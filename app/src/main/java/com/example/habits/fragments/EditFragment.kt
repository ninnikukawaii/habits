package com.example.habits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.habits.MainActivity
import com.example.habits.R
import com.example.habits.models.Habit
import com.example.habits.models.HabitLists
import com.example.habits.models.HabitType
import com.example.habits.models.PriorityLevel
import com.example.habits.view_models.EditViewModel
import kotlinx.android.synthetic.main.edit_fragment.*

class EditFragment : Fragment() {

    private val viewModel: EditViewModel by viewModels()

    companion object {
        const val TYPE = "ACTION"
    }

    enum class Action {
        ADD,
        EDIT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val habit: Habit = it.getSerializable(Habit.HABIT) as? Habit
                ?: Habit("", "", PriorityLevel(1), HabitType.Good, -1, -1)

            viewModel.name.value = habit.name
            viewModel.description.value = habit.description
            viewModel.priority.value = habit.priority.priority
            viewModel.type.value = habit.type.name
            viewModel.period.value = habit.period
            viewModel.amount.value = habit.amount
            viewModel.id.value = habit.id

            viewModel.actionType.value = it.getString(TYPE, "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val enumActionType = try {
            Action.valueOf(viewModel.actionType.value ?: "")
        } catch (e: IllegalArgumentException) {
            alertIncorrectData("Unknown action type '${viewModel.actionType.value}'")
            findNavController().popBackStack()
            null
        }

        observeInitialize()

        ArrayAdapter.createFromResource(
            context!!,
            R.array.priorities,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        saveButton.setOnClickListener {
            if (activity is MainActivity && enumActionType != null) {
                validate(enumActionType)
            }
        }
    }

    private fun observeInitialize() {
        viewModel.name.observe(viewLifecycleOwner, Observer { name -> nameInput.setText(name) })
        viewModel.description.observe(viewLifecycleOwner, Observer { description -> descriptionInput.setText(description) })

        viewModel.priority.observe(viewLifecycleOwner, Observer { priority ->
            val position = priority ?: 1
            spinner.setSelection(position - 1)
        })

        viewModel.type.observe(viewLifecycleOwner, Observer { type -> when (type) {
            HabitType.Good.name -> good.isChecked = true
            HabitType.Bad.name -> bad.isChecked = true
        } })

        viewModel.period.observe(viewLifecycleOwner, Observer { period ->
            val periodSting = if (period != null && period == -1) "" else period.toString()
            periodInput.setText(periodSting)
        })

        viewModel.amount.observe(viewLifecycleOwner, Observer { amount ->
            val amountString = if (amount != null && amount == -1) "" else amount.toString()
            amountInput.setText(amountString)
        })
    }

    private fun validate(action: Action) {
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
            Action.ADD -> HabitLists.INSTANCE.add(
                Habit(habitName, habitDescription, habitPriority, habitType, habitPeriod, habitAmount)
            )
            Action.EDIT -> {
                val habit = Habit(habitName, habitDescription, habitPriority, habitType, habitPeriod, habitAmount)
                habit.id = viewModel.id.value
                HabitLists.INSTANCE.edit(habit)
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
