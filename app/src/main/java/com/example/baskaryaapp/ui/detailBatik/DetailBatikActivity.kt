package com.example.baskaryaapp.ui.detailBatik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.database.BookmarkBatik
import com.example.baskaryaapp.data.response.BatikItem
import com.example.baskaryaapp.databinding.ActivityDetailBatikBinding
import com.example.baskaryaapp.ui.BatikViewModelFactory
import com.example.baskaryaapp.ui.BookmarkBatikViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailBatikActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBatikBinding
    private val detailBatikViewModel by viewModels<DetailBatikViewModel>(){
        BookmarkBatikViewModelFactory.getInstance(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBatikBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val batik = intent.getParcelableExtra<BatikItem>(EXTRA_BATIK)
        batik?.let {
            Glide.with(this)
                .load(it.imageUrl)
                .into(binding.imgItemPhoto)
            binding.tvItemTitle.text = it.title
            binding.tvItemSubTitle.text = it.origin
            binding.tvItemDescription.text = it.description
        }

//        detailBatikViewModel.getBatik(intent.getStringExtra(EXTRA_ID))
        val id = intent.getIntExtra("key_id", 0)
//        val id = intent.getIntExtra(EXTRA_ID, 0)
        val title = intent.getStringExtra("key_title").toString()
        val imageUrl = intent.getStringExtra("key_imageUrl").toString()

        var bookmark = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = detailBatikViewModel.isBookmarked(id)
            withContext(Dispatchers.Main){
                if (count > 0){
                    binding.icBookmark.setImageResource(R.drawable.ic_bookmarked)
                    bookmark = true
                }else{
                    binding.icBookmark.setImageResource(R.drawable.ic_unbookmarked)
                    bookmark = false
                }
            }
        }

        binding.icBookmark.setOnClickListener{
            bookmark = !bookmark
            if (bookmark){
                binding.icBookmark.setImageResource(R.drawable.ic_bookmarked)
                detailBatikViewModel.saveBatik(BookmarkBatik(id, title, imageUrl))
            }else{
                binding.icBookmark.setImageResource(R.drawable.ic_unbookmarked)
                detailBatikViewModel.deleteBatik(id)
            }
        }
        Log.d("DetailBatikActivity", "ID: $id")
        Log.d("DetailBatikActivity", "title: $title")
        Log.d("DetailBatikActivity", "imageurl: $imageUrl")
    }

    companion object {
        const val EXTRA_ID = "key_id"
        const val EXTRA_BATIK = "key_batik"
    }
}