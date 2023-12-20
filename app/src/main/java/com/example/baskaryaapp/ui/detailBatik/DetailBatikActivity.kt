package com.example.baskaryaapp.ui.detailBatik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.helper.FirebaseHelper
import com.example.baskaryaapp.data.response.BatikItem
import com.example.baskaryaapp.databinding.ActivityDetailBatikBinding
import com.google.firebase.auth.FirebaseAuth

class DetailBatikActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBatikBinding
    private val firebaseHelper = FirebaseHelper()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var batikList: MutableList<BatikItem>

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
//        val id = intent.getIntExtra("key_id", 0)
//        val id = intent.getIntExtra(EXTRA_ID, 0)
        val id = intent.getStringExtra("key_id").toString()
        val title = intent.getStringExtra("key_title").toString()
        val imageUrl = intent.getStringExtra("key_imageUrl").toString()
        batikList = mutableListOf()

        var bookmark = false
//        CoroutineScope(Dispatchers.IO).launch {
//            val count = detailBatikViewModel.isBookmarked(id)
//            withContext(Dispatchers.Main){
//                val userId = auth.currentUser?.uid
//                if (userId != null) {
//                    if (count > 0){
//                        binding.icBookmark.setImageResource(R.drawable.ic_bookmarked)
//                        bookmark = true
//                    }else{
//                        binding.icBookmark.setImageResource(R.drawable.ic_unbookmarked)
//                        bookmark = false
//                    }
//                }
//            }
//        }

        if (batik?.isBookmarked == true) {
            binding.icBookmark.setImageResource(R.drawable.ic_bookmarked)
            bookmark = true
        } else {
            binding.icBookmark.setImageResource(R.drawable.ic_unbookmarked)
            bookmark = false
        }

        binding.icBookmark.setOnClickListener {
            bookmark = !bookmark
            if (bookmark) {
                binding.icBookmark.setImageResource(R.drawable.ic_bookmarked)
                firebaseHelper.addBookmarkBatik(id, title, imageUrl, batikList)
            } else {
                binding.icBookmark.setImageResource(R.drawable.ic_unbookmarked)
                firebaseHelper.removeBookmarkBatik(id, title, imageUrl, batikList)
            }
        }

//        binding.icBookmark.setOnClickListener{
//            bookmark = !bookmark
//            val userId = auth.currentUser?.uid
//            if (userId != null) {
//                if (bookmark) {
//                    binding.icBookmark.setImageResource(R.drawable.ic_bookmarked)
//                    detailBatikViewModel.saveBatik(BookmarkBatik(id, title, imageUrl))
//                } else {
//                    binding.icBookmark.setImageResource(R.drawable.ic_unbookmarked)
//                    detailBatikViewModel.deleteBatik(id)
//                }
//            }
//        }
        Log.d("DetailBatikActivity", "ID: $id")
        Log.d("DetailBatikActivity", "title: $title")
        Log.d("DetailBatikActivity", "imageurl: $imageUrl")
    }

    companion object {
        const val EXTRA_ID = "key_id"
        const val EXTRA_BATIK = "key_batik"
    }
}