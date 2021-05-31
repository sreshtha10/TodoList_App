package com.example.todolistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(val todos :MutableList<String> ): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    inner class ViewHolder(item_view:View) : RecyclerView.ViewHolder(item_view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        val holder = ViewHolder(view)
        holder.itemView.ivTodo.setOnClickListener{
            val position = todos.indexOf(holder.itemView.tvTodo.text.toString())
            val task = holder.itemView.tvTodo.text.toString()
            val databaseHelper = DatabaseHelper(parent.context)
            databaseHelper.deleteTask(task)
            todos.removeAt(position)

            notifyItemRemoved(position)
            notifyItemRangeChanged(position,todos.size)

        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            tvTodo.text = todos[position]
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }


}