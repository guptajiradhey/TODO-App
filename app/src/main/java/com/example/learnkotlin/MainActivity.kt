package com.example.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnkotlin.API.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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



        fetchtoDoitems()




        btnadd.setOnClickListener {
            val text = editText.text.toString()
            if (text.isEmpty())
                Toast.makeText(this, "Please Write Something", Toast.LENGTH_SHORT).show()
            else {
                todolist.add(TODO(text))
                adapter.notifyDataSetChanged()
                editText.text.clear()

            }
        }
        btndelete.setOnClickListener {
            Toast.makeText(this, "Files will be deleted", Toast.LENGTH_SHORT).show()
            todolist.removeAll { todo -> todo.completed }
            adapter.notifyDataSetChanged()
        }

    }

    fun fetchtoDoitems() {
        val response=  RetrofitInstance.api.getTODOS();

        response.enqueue( object : Callback<List<TODO>> {

            override fun onResponse(call: Call<List<TODO>>?, response: Response<List<TODO>>?) {
                if(response?.body() != null){
                    todolist.clear()
                    todolist = (response.body() as MutableList<TODO>?)!!

                }
                setUpRecyclerView()
            }

            override fun onFailure(call: Call<List<TODO>>?, t: Throwable?) {

            }
        })

    }


    fun setUpRecyclerView(){
      Toast.makeText(this,"Recycler View Called",Toast.LENGTH_SHORT).show()
        recyclerview.layoutManager = LinearLayoutManager(this);
        adapter = RecyclerAdapter(todolist)
        recyclerview.adapter = adapter;
    }


}