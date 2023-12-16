package com.example.baskaryaapp.ui.bookmark.customization

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
import com.example.baskaryaapp.data.response.ArticlesItem
import com.example.baskaryaapp.databinding.ItemListArticlesBinding
import com.example.baskaryaapp.databinding.ItemListCustomizationBinding
import com.example.baskaryaapp.ui.detailArticle.DetailArticleActivity

class CustomizationAdapter : ListAdapter<ArticlesItem, CustomizationAdapter.ListViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListCustomizationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val articles = getItem(position)
        holder.bind(articles)
    }

    class ListViewHolder(private val binding: ItemListCustomizationBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(articles: ArticlesItem){
            Glide.with(binding.root.context)
                .load(articles.imageUrl)
                .into(binding.imgItemPhoto)

            binding.root.setOnClickListener{
                val intentDetail = Intent(binding.root.context, DetailArticleActivity::class.java)
                intentDetail.putExtra(EXTRA_ID, articles.id)
                intentDetail.putExtra(EXTRA_ARTICLES, articles)

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(binding.imgItemPhoto, "image"),
                    )

                itemView.context.startActivity(intentDetail, optionsCompat.toBundle())
            }
        }
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>(){
            override fun areItemsTheSame(
                oldItem: ArticlesItem,
                newItem: ArticlesItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ArticlesItem,
                newItem: ArticlesItem
            ): Boolean {
                return oldItem == newItem
            }
        }

        const val EXTRA_ID = "key_id"
        const val EXTRA_ARTICLES = "key_articles"
    }
}