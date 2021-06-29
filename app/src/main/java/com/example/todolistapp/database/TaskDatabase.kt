package com.example.todolistapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistapp.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase :RoomDatabase() {
    abstract fun taskDao(): TasksDAO

    companion object{
        
    }
}