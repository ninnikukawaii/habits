package com.example.habits

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.view.children
import com.example.habits.habit.HabitType
import com.example.habits.habit.PriorityLevel
import kotlinx.android.synthetic.main.edit_fragment.*

class AddingActivity: Activity() {

    companion object FieldNames {
        const val NAME = "NAME"
        const val DESCRIPTION = "DESCRIPTION"
        const val PRIORITY= "PRIORITY"
        const val TYPE = "TYPE"
        const val PERIOD = "PERIOD"
        const val AMOUNT = "AMOUNT"
        const val INDEX = "INDEX"
    }

    private var type = MainActivity.ADD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_fragment)
        initialize()
        saveButton.setOnClickListener(this::handle)
    }

    private fun initialize() {
        val index = intent.getIntExtra(INDEX, -1)
        if (index == -1)
            return

        val habitName = intent.getStringExtra(NAME)
        nameInput.setText(habitName)
        val habitDescription = intent.getStringExtra(DESCRIPTION)
        descriptionInput.setText(habitDescription)
        val habitPriority = intent.getIntExtra(PRIORITY, 0)
        spinner.setSelection(habitPriority - 1)
        val habitType = intent.getStringExtra(TYPE)

        for (button in radio.children) {
            if (button is RadioButton)
                if (button.text == habitType)
                    button.isChecked = true
        }
        val habitPeriod = intent.getIntExtra(PERIOD, 0)
        periodInput.setText(habitPeriod.toString())
        val habitAmount = intent.getIntExtra(AMOUNT, 0)
        amountInput.setText(habitAmount.toString())

        type = MainActivity.EDIT
    }

    private fun handle(view: View) {
        try {
            val habitName = nameInput.text.toString()
            assert(habitName.isNotEmpty())
            val habitDescription = descriptionInput.text.toString()
            assert(habitDescription.isNotEmpty())
            val habitPriority = spinner.selectedItem.toString().toInt()
            PriorityLevel(habitPriority)

            val radioView = radio
            val button = findViewById<RadioButton>(radioView.checkedRadioButtonId)
            val idx = radioView.indexOfChild(button)
            val habitType = (radioView.getChildAt(idx) as RadioButton).text?.toString() ?: ""
            HabitType.valueOf(habitType)

            val habitPeriod = periodInput.text.toString().toInt()
            assert(habitPeriod > 0)
            val habitAmount = amountInput.text.toString().toInt()
            assert(habitAmount > 0)

            val addingIntent = Intent()
            addingIntent.putExtra(NAME, habitName)
            addingIntent.putExtra(DESCRIPTION, habitDescription)
            addingIntent.putExtra(PRIORITY, habitPriority)
            addingIntent.putExtra(TYPE, habitType)
            addingIntent.putExtra(PERIOD, habitPeriod)
            addingIntent.putExtra(AMOUNT, habitAmount)

            if (type == MainActivity.EDIT)
                addingIntent.putExtra(INDEX, intent.getIntExtra(INDEX, -1))

            setResult(type, addingIntent)
            finish()
        }
        catch (ex: Exception) {
            alertIncorrectData()
        }
    }

    private fun alertIncorrectData() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Incorrect input")
        builder.setMessage("Your input is incorrect!")
        builder.setPositiveButton("OK") { dialog, _ -> dialog.cancel() }
        builder.show()
    }
}