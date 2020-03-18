package com.example.habits.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    //var callbackCreatedRecycleView: CreatedRecycleViewListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //callbackCreatedRecycleView = activity as CreatedRecycleViewListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*recyclerView.adapter = HabitAdapter(emptyList()) { position: Int, habit ->
            val startSecondActivity = Intent(
                context,
                AddingActivity::class.java
            )
            this.startActivityForResult(startSecondActivity, MainActivity.EDIT_HABIT_REQUEST_CODE)
        }*/

        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
        //callbackCreatedRecycleView?.recycleViewCreated(recyclerView)
    }
}

interface CreatedRecycleViewListener {
    fun recycleViewCreated(view: RecyclerView)
}
