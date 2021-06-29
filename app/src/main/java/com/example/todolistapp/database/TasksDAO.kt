package com.example.todolistapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolistapp.model.Task

@Dao
interface TasksDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task):Long

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAlTasks():LiveData<List<Task>>
}