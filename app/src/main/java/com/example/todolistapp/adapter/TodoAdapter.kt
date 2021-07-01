package com.example.todolistapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.databinding.ItemTodoBinding
import com.example.todolistapp.model.Task
import com.example.todolistapp.ui.activities.MainActivity
import com.example.todolistapp.ui.fragments.ViewFragment


class TodoAdapter(): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {


    inner class ViewHolder(val binding:ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(
            oldItem: Task,
            newItem: Task
        ): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this,diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )



        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.setOnClickListener{

            val task = differ.currentList[position]
            val activity = it.context as MainActivity
            val viewFragment = ViewFragment(task)
            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragments,viewFragment)
                commit()
            }

        }
        holder.binding.apply {
            tvTodo.text = differ.currentList[position].heading
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



}