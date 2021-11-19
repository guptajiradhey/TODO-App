package com.example.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var btnaddd: Button
    lateinit var btndelete: Button
    lateinit var editText: EditText

    //    var todoList= listOf<TODO>()
    var todolist: MutableList<TODO> = mutableListOf()
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnaddd = findViewById(R.id.btnadd)
        btndelete = findViewById(R.id.btndelete)
        editText = findViewById(R.id.editText)


        recyclerview.layoutManager = LinearLayoutManager(this);
        todolist.add(TODO("Dummy Task 1", false))
        todolist.add(TODO("Dummy Task 2", true))
        adapter = RecyclerAdapter(todolist, this)
        recyclerview.adapter = adapter;



        btnadd.setOnClickListener {
            val text = editText.text.toString()
            if (text.isEmpty())
                Toast.makeText(this, "Please Write Something", Toast.LENGTH_SHORT).show()
            else {
                todolist.add(TODO(text, false))
                adapter.notifyDataSetChanged()
                editText.text.clear()

            }
        }
        btndelete.setOnClickListener {
           Toast.makeText(this,"Files will be deleted",Toast.LENGTH_SHORT).show()
            todolist.removeAll { todo -> todo.isCompleted }
            adapter.notifyDataSetChanged()
        }

    }
}