package com.example.todolistapp.ui.fragments

import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.adapter.TodoAdapter
import com.example.todolistapp.databinding.FragmentDisplayTodoBinding
import com.example.todolistapp.model.TaskViewModel


class DisplayTodoFragment:Fragment() {


    private var binding:FragmentDisplayTodoBinding? = null
    private lateinit var adapter: TodoAdapter
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplayTodoBinding.inflate(inflater,container,false)
        adapter = TodoAdapter()
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        // check if there are tasks in the db or not.
        mTaskViewModel.readAllTask.observe(viewLifecycleOwner, Observer {
            adapter.differ.submitList(it)
            adapter.notifyDataSetChanged()
        })

        binding?.btnAddTodo?.setOnClickListener {
            val addTodoFragment = AddTodoFragment()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.flFragments,addTodoFragment)
                commit()
            }
        }

        setUpSwipeToDelete()


    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    private fun setUpRecyclerView(){
        binding?.apply {
            rvTodo.adapter = adapter
            rvTodo.layoutManager = LinearLayoutManager(activity)
        }
    }


    private fun setUpSwipeToDelete(){

        val itemTouchCallback = object: ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ){
            private val deleteIcon = ContextCompat.getDrawable(activity!!.baseContext,R.drawable.ic_delete)
            private val background = ColorDrawable()
            private val intrinsicHeight = deleteIcon?.intrinsicHeight
            private val intrinsicWidth = deleteIcon?.intrinsicWidth
            private val backgroundColor = Color.parseColor("#f44336")
            private val clearPaint = Paint().apply {
                xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            }


            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                removeAt(viewHolder.bindingAdapterPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                val itemView = viewHolder.itemView
                val itemHeight = itemView.bottom - itemView.top
                val isCanceled = dX == 0f && !isCurrentlyActive

                if(isCanceled){
                    clearCanvas(c,itemView.right+dX,itemView.top.toFloat(), itemView.right.toFloat(),itemView.bottom.toFloat())
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    return
                }


                background.color = backgroundColor
                background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                background.draw(c)

                // Calculate position of delete icon
                val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight!!) / 2
                val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
                val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth!!
                val deleteIconRight = itemView.right - deleteIconMargin
                val deleteIconBottom = deleteIconTop + intrinsicHeight

                // Draw the delete icon
                deleteIcon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
                deleteIcon?.draw(c)


                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
                c?.drawRect(left, top, right, bottom, clearPaint)
            }

        }

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding!!.rvTodo)




    }


    private fun removeAt(position: Int){
        mTaskViewModel.deleteTask(adapter.differ.currentList[position])

    }


}