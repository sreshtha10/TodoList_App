package com.example.todolistapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentViewBinding
import com.example.todolistapp.model.Task
import com.example.todolistapp.model.TaskViewModel

class ViewFragment: Fragment() {

    private var binding:FragmentViewBinding?= null
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewBinding.inflate(inflater,container,false)
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.btnDelete.setOnClickListener {
            val heading = binding!!.tvHeading.text.toString()
            val priority = binding!!.tvDescription.text.toString()
            val desc = binding!!.tvDescription.text.toString()
            mTaskViewModel.deleteTask(Task(0,heading,desc,priority.toInt()))

            Toast.makeText(
                activity,
                "Deleted Successfully",
                Toast.LENGTH_SHORT
            ).show()
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