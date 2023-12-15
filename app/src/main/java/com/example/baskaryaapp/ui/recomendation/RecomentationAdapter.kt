package com.example.baskaryaapp.ui.recomendation

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baskaryaapp.data.response.DataItem
import com.example.baskaryaapp.databinding.ItemListBatikNotextBinding
import com.example.baskaryaapp.ui.detailBatik.DetailBatikActivity

class RecomentationAdapter : ListAdapter<DataItem, RecomentationAdapter.ListViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListBatikNotextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        tes
//        holder.courseNameTV.text = courseList.get(position).batikName
//        holder.courseIV.setImageResource(courseList.get(position).batikImg)
        val batik = getItem(position)
        holder.bind(batik)
    }

    class ListViewHolder(private val binding: ItemListBatikNotextBinding) : RecyclerView.ViewHolder(binding.root) {

        //        val courseNameTV: TextView = itemView.findViewById(R.id.idTVBatik)

        //        val courseNameTV: TextView = itemView.findViewById(R.id.idTVBatik)
//        val courseIV: ImageView = itemView.findViewById(R.id.idIVBatik)
        fun bind(batik: DataItem){
            Glide.with(binding.root.context)
                .load(batik?.imageUrl)
                .into(binding.idIVBatik)

            binding.root.setOnClickListener{
                val intentDetail = Intent(binding.root.context, DetailBatikActivity::class.java)
                intentDetail.putExtra(EXTRA_ID, batik.id)
                intentDetail.putExtra(EXTRA_BATIK, batik)

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(binding.idIVBatik, "image"),
                    )

                itemView.context.startActivity(intentDetail, optionsCompat.toBundle())
            }
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
        const val EXTRA_BATIK = "key_batik"
    }
}
