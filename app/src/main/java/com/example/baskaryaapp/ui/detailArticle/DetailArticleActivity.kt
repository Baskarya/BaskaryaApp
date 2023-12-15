package com.example.baskaryaapp.ui.detailArticle

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import com.bumptech.glide.Glide
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.response.DataItem
import com.example.baskaryaapp.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val articles = intent.getParcelableExtra<DataItem>(EXTRA_ARTICLES)
//        articles?.let {
//            Glide.with(this)
//                .load(it.imageUrl)
//                .into(binding.imgItemPhoto)
//            binding.tvItemTitle.text = it.title
//            binding.tvItemSubTitle.text = it.author
//            binding.tvItemDescription.text = it.content
//        }
    }

    companion object {
        const val EXTRA_ARTICLES = "key_articles"
    }
}