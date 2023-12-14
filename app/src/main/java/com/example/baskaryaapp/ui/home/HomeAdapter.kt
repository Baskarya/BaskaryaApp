package com.example.baskaryaapp.ui.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baskaryaapp.R

class HomeAdapter(var data: ArrayList<BatikRvModel>,var context: Activity?):RecyclerView.Adapter<HomeAdapter.MyViewHolder>(){



    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val courseName = view.findViewById<TextView>(R.id.idTVBatik)
        val courseImg = view.findViewById<ImageView>(R.id.idIVBatik)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val view : View= LayoutInflater.from(parent.context).inflate(R.layout.item_list_batik,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (data.size > 3) 3 else data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.courseName.text = data[position].courseName
        holder.courseImg.setImageResource(data[position].courseImg)
    }

}
