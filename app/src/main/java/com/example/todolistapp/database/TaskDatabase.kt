package com.example.todolistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolistapp.model.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase :RoomDatabase() {
    abstract fun taskDao(): TasksDAO

    companion object{
        @Volatile
        private var INSTANCE:TaskDatabase? = null

        fun getDatabase(context: Context):TaskDatabase{
            val tmpInstance = INSTANCE
            if(tmpInstance != null){
                return tmpInstance
            }
            else{
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "tasks_db.db"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }

    }
}
