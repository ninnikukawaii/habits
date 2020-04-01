package com.example.habits.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditViewModel : ViewModel() {
    val actionType = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val priority = MutableLiveData<Int>()
    val type = MutableLiveData<String>()
    val period = MutableLiveData<Int>()
    val amount = MutableLiveData<Int>()
    val id = MutableLiveData<Int>()
}
