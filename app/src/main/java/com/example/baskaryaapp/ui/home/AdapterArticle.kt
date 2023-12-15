package com.example.baskaryaapp.ui.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baskaryaapp.R

class AdapterArticle (var data: ArrayList<ArticleModel>,var context: Activity?): RecyclerView.Adapter<AdapterArticle.MyViewHolder>(){
    class MyViewHolder (view:View):RecyclerView.ViewHolder(view) {
        val articleName = view.findViewById<TextView>(R.id.tv_item_title)
        val articleIsi = view.findViewById<TextView>(R.id.tv_item_description)
        val articleImg= view.findViewById<ImageView>(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view : View= LayoutInflater.from(parent.context).inflate(R.layout.item_list_articles,parent,false)
        return AdapterArticle.MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (data.size > 1) 1 else data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.articleName.text = data[position].articleName
        holder.articleIsi.text = data[position].articleIsi
        holder.articleImg.setImageResource(data[position].articleImg)
    }

}