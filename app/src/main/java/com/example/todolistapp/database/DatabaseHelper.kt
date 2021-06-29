package com.example.todolistapp.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    "todoDatabase",
    null,
    1
) {

    val table_name = "todoTable"
    val todoId = "ID"
    val todoTask = "TASK"


    override fun onCreate(db: SQLiteDatabase?) {

        val createTablequery = "CREATE TABLE ${table_name} ( ${todoId} INTEGER PRIMARY KEY AUTOINCREMENT, ${todoTask} TEXT)"
        db?.execSQL(createTablequery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            // empty for now. Used when we upgrade our app version
    }


    fun insertTask(task : String):Boolean{
        val db = writableDatabase

        val cv = ContentValues()

        cv.put(todoTask,task)

        val insert = db.insert(table_name,null,cv)

        return !insert.equals(-1)
    }


    @SuppressLint("Recycle")
    fun deleteTask(task: String):Boolean{

        val db = writableDatabase
        val query = "DELETE FROM ${table_name} WHERE ${todoTask} =\"${task}\""

        val cursor = db.rawQuery(query,null)

        return cursor.moveToFirst()

    }


    fun getTasks():MutableList<String>{
        val returnList = ArrayList<String>()

        val db = readableDatabase

        val query = "SELECT * FROM $table_name"

        val cursor = db.rawQuery(query,null)

        if(cursor.moveToFirst()){
            do {
                val task = cursor.getString(1)
                returnList.add(task)
            }while(cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return returnList
    }
}

