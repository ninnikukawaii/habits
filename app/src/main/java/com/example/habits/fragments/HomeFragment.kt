package com.example.habits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habits.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply { arguments = Bundle().apply { } }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_editHabitFragment)
        }

        /*recyclerView.adapter = HabitAdapter(emptyList()) { position: Int, habit ->
            val startSecondActivity = Intent(
                context,
                AddingActivity::class.java
            )
            this.startActivityForResult(startSecondActivity, MainActivity.EDIT_HABIT_REQUEST_CODE)
        }*/

        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        list.addItemDecoration(dividerItemDecoration)
        //callbackCreatedRecycleView?.recycleViewCreated(recyclerView)
    }
}
