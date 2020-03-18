package com.example.habits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.habits.R

class FragmentGoodHabits : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FragmentGoodHabits().apply { arguments = Bundle().apply { } }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_good_habits, container, false)
    }
}
