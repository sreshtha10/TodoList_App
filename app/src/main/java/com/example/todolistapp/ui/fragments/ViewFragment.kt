package com.example.todolistapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolistapp.databinding.FragmentViewBinding

class ViewFragment: Fragment() {

    private var binding:FragmentViewBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}