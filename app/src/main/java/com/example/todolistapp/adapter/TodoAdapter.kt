package com.example.todolistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.databinding.ItemTodoBinding
import com.example.todolistapp.model.Task



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

        holder.binding.root.setOnClickListener{
            // display full task
            
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvTodo.text = differ.currentList[position].heading
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



}