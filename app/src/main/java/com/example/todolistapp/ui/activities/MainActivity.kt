package com.example.todolistapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.database.DatabaseHelper
import com.example.todolistapp.R
import com.example.todolistapp.adapter.TodoAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseHelper = DatabaseHelper(this)
        val todoList = databaseHelper.getTasks()


        val adapter = TodoAdapter(todoList)
        rvTodo.adapter = adapter
        rvTodo.layoutManager = LinearLayoutManager(this)


        btnAddTodo.setOnClickListener {
            todoList.add(etTodo.text.toString())
            databaseHelper.insertTask(etTodo.text.toString())
            adapter.notifyItemInserted(todoList.size-1)
            etTodo.text.clear()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuCloseApp -> finish()
            R.id.menuAbout ->{
                Intent(this@MainActivity, AboutActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return true
    }
}