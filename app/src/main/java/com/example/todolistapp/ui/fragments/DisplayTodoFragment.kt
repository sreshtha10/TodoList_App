package com.example.todolistapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentDisplayTodoBinding

class DisplayTodoFragment:Fragment() {

    private var binding:FragmentDisplayTodoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplayTodoBinding.inflate(inflater,container,false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnAddTodo?.setOnClickListener {
            val addTodoFragment = AddTodoFragment()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.flFragments,addTodoFragment)
                commit()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}