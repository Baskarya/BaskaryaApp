package com.example.baskaryaapp.ui.recomendation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.response.SimilarImagesItem
import com.example.baskaryaapp.databinding.ItemListBatikNotextBinding

class RecomentationAdapter :
    ListAdapter<SimilarImagesItem, RecomentationAdapter.UploadResponseViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UploadResponseViewHolder {
        val binding = ItemListBatikNotextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UploadResponseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UploadResponseViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class UploadResponseViewHolder(private val binding: ItemListBatikNotextBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(similarImage: SimilarImagesItem) {
            binding.apply {
                // Load gambar ke ImageView menggunakan Glide
                similarImage.url?.let { url ->
                    Glide.with(itemView)
                        .load(url)
                        .placeholder(R.drawable.baskarya_logo) // Gambar placeholder jika URL tidak valid
                        .into(idIVBatik)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<SimilarImagesItem>() {
        override fun areItemsTheSame(oldItem: SimilarImagesItem, newItem: SimilarImagesItem): Boolean {
            // Jika id atau identifier item digunakan, periksa di sini
            return oldItem.namaBatik == newItem.namaBatik // Ganti dengan identifier yang sesuai jika ada
        }

        override fun areContentsTheSame(oldItem: SimilarImagesItem, newItem: SimilarImagesItem): Boolean {
            // Periksa apakah konten dari item sama di sini (jika perlu)
            return oldItem == newItem
        }
    }
}