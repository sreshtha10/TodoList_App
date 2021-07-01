package com.example.todolistapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "tasks"
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val heading:String,
    val description: String,
    val priority: Int
)



