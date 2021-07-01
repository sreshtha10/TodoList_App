package com.example.todolistapp.repository

import androidx.lifecycle.LiveData
import com.example.todolistapp.database.TasksDAO
import com.example.todolistapp.model.Task

class TaskRepo(private val tasksDAO: TasksDAO) {
    val readAllTask : LiveData<MutableList<Task>> = tasksDAO.getAlTasks()

    suspend fun addTask(task: Task){
        tasksDAO.insertTask(task)
    }

    suspend fun deleteTask(task: Task){
        tasksDAO.deleteTask(task)
    }

}