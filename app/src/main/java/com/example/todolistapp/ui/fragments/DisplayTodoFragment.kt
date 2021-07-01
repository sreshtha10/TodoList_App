package com.example.todolistapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.R
import com.example.todolistapp.adapter.TodoAdapter
import com.example.todolistapp.databinding.FragmentDisplayTodoBinding
import com.example.todolistapp.model.TaskViewModel

class DisplayTodoFragment:Fragment() {


    private var binding:FragmentDisplayTodoBinding? = null
    private lateinit var adapter: TodoAdapter
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplayTodoBinding.inflate(inflater,container,false)
        adapter = TodoAdapter()
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        // check if there are tasks in the db or not.
        mTaskViewModel.readAllTask.observe(viewLifecycleOwner, Observer {
            adapter.differ.submitList(it)
            adapter.notifyDataSetChanged()
        })

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


    private fun setUpRecyclerView(){
        binding?.apply {
            rvTodo.adapter = adapter
            rvTodo.layoutManager = LinearLayoutManager(activity)
        }
    }

}