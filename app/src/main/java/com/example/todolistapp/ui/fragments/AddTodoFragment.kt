package com.example.todolistapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentAddTodoBinding
import com.example.todolistapp.model.Task
import com.example.todolistapp.model.TaskViewModel

class AddTodoFragment:Fragment() {

    private var binding:FragmentAddTodoBinding? = null
    private lateinit var mTaskViewModel:TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTodoBinding.inflate(inflater,container,false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.btnAddTask.setOnClickListener {

            if(checkEmptyFields()){
                Toast.makeText(
                    activity,
                    "Empty Fields",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // add task to database & update recycler view
            val desc = binding!!.etDescription.text.toString()
            val heading = binding!!.etHeading.text.toString()
            val priority = binding!!.etPriority.text.toString().toInt()

            val task = Task(0,heading,desc,priority)

            mTaskViewModel.addTask(task)
            Toast.makeText(
                activity,
                "Task Added",
                Toast.LENGTH_SHORT
            ).show()


            val displayTodoFragment = DisplayTodoFragment()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.flFragments,displayTodoFragment)
                commit()
            }

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

    private fun checkEmptyFields():Boolean{
        return binding?.etDescription?.text.toString() == "" && binding?.etHeading?.text?.toString()  == "" && binding?.etPriority?.text?.toString() == ""
    }
}