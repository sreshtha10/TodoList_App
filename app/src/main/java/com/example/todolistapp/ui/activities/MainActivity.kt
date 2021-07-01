package com.example.todolistapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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




}