package com.example.baskaryaapp.ui.batikpedia

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.response.DataItem
import com.example.baskaryaapp.databinding.ItemListBatikBinding


class BatikRVAdapter(
    // on below line we are passing variables
    // as course list and context
    private val courseList: ArrayList<BatikRVModal>,
    private val context: BatikpediaActivity
) : RecyclerView.Adapter<BatikRVAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BatikRVAdapter.CourseViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_batik_list,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return CourseViewHolder(itemView)


class BatikRVAdapter : ListAdapter<DataItem, BatikRVAdapter.ListViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BatikRVAdapter.ListViewHolder {
        val binding = ItemListBatikBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)

    }

    override fun onBindViewHolder(holder: BatikRVAdapter.ListViewHolder, position: Int) {
//        tes
//        holder.courseNameTV.text = courseList.get(position).batikName
//        holder.courseIV.setImageResource(courseList.get(position).batikImg)
        val batik = getItem(position)
        holder.bind(batik)
    }

    class ListViewHolder(private val binding: ItemListBatikBinding) : RecyclerView.ViewHolder(binding.root) {
        val courseNameTV: TextView = itemView.findViewById(R.id.idTVCourse)
        val courseIV: ImageView = itemView.findViewById(R.id.idIVCourse)
        fun bind(batik: DataItem){
            Glide.with(binding.root.context)
                .load(batik.dataDokumen?.url)
                .into(binding.idIVCourse)
            binding.idTVCourse.text = batik.dataDokumen?.title
        }
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>(){
            override fun areItemsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }
        }

        const val EXTRA_ID = "key_id"
//        const val EXTRA_STORY = "key_story"
    }
}