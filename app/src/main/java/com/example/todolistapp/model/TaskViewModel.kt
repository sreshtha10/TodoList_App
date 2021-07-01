package com.example.todolistapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.database.TaskDatabase
import com.example.todolistapp.repository.TaskRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application):AndroidViewModel(application) {

    val readAllTask : LiveData<MutableList<Task>>
    private val repository: TaskRepo

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepo(taskDao)
        readAllTask = repository.readAllTask
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }
}