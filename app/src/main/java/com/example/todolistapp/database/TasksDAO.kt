package com.example.todolistapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolistapp.model.Task

@Dao
interface TasksDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task):Long

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks ORDER BY priority")
    fun getAlTasks():LiveData<MutableList<Task>>
}