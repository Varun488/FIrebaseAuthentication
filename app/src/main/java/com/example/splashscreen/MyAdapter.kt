package com.example.splashscreen

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyAdapter(private val noteList : ArrayList<NoteData>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.eachitem, parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = noteList[position]

        holder.tvhead.text = currentitem.Title
        holder.tvdate.text = currentitem.Date

    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
          val tvhead : TextView = itemView.findViewById(R.id.tvhead)
          val tvdate : TextView = itemView.findViewById(R.id.tvdate)

    }

}