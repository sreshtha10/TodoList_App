package com.example.todolistapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentAddTodoBinding

class AddTodoFragment:Fragment() {

    private var binding:FragmentAddTodoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTodoBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.btnAddTask.setOnClickListener {
            // add task to database & update recycler view
        }

        binding!!.btnCancel.setOnClickListener {
            val displayTodoFragment = DisplayTodoFragment()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.flFragments,displayTodoFragment)
                commit()
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}