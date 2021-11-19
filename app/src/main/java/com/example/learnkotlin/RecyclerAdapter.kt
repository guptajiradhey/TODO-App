package com.example.learnkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todoitem.view.*
import retrofit2.Callback

class RecyclerAdapter(
    var list: MutableList<TODO>
)  : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.todoitem,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo:TODO=list[position]
        val title:String= todo.id.toString()+todo.title
        holder.itemView.tvTodoTitle.text=title;
        holder.itemView.cbDone.isChecked=todo.completed
//        holder.itemView.cbDone.setOnCheckedChangeListener{_,ischeck->
//            todo.completed=!todo.completed
//            notifyDataSetChanged()
//        }
    }
}