package com.example.todolistapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.todolistapp.R
import com.example.todolistapp.databinding.ActivityMainBinding
import com.example.todolistapp.ui.fragments.DisplayTodoFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val displayTodoFragment = DisplayTodoFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(binding.flFragments.id,displayTodoFragment)
            commit()
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