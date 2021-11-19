package com.example.learnkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todoitem.view.*

class RecyclerAdapter(
    var list: MutableList<TODO>,
   val  context: Context
)  : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.todoitem,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo:TODO=list[position]
        holder.itemView.tvTodoTitle.text=todo.text;
        holder.itemView.cbDone.isChecked=todo.isCompleted
        holder.itemView.cbDone.setOnCheckedChangeListener{_,ischeck->
            todo.isCompleted=!todo.isCompleted
            notifyDataSetChanged()
        }
    }
}