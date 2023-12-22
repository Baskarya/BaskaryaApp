package com.example.baskaryaapp.ui.detailCustomization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.helper.FirebaseHelper
import com.example.baskaryaapp.data.response.Data
import com.example.baskaryaapp.databinding.ActivityCustomizationBinding
import com.example.baskaryaapp.databinding.ActivityDetailCustomizationBinding
import com.example.baskaryaapp.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class DetailCustomizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCustomizationBinding
    private val firebaseHelper = FirebaseHelper()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var customList: MutableList<Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCustomizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView2.setOnClickListener{back()}
        // Mengambil data yang dikirimkan dari RecomendationFragment
        val id = intent.getStringExtra("key_id").toString()
        val namaBatik = intent.getStringExtra("NAMA_BATIK").toString()
        val imageUrl = intent.getStringExtra("IMAGE_URL").toString()
        val custom = intent.getParcelableExtra<Data>("key_custom")

        Glide.with(this)
            .load(imageUrl)
            .into(binding.ivCustom)
        binding.tvPattern.text = "$namaBatik Pattern"

        customList = mutableListOf()

        var bookmark = false

        if (custom?.isBookmarked == true) {
            bookmark = true
        } else {
            bookmark = false
        }

//        binding.btnDelete.setOnClickListener {
//            bookmark = !bookmark
//            if (bookmark) {
//                firebaseHelper.addBookmarkCustom(id, namaBatik, imageUrl, customList)
//                showToastAndNavigate(getString(R.string.bookmark_added))
//            } else {
//                firebaseHelper.removeBookmarkCustom(id, namaBatik, imageUrl, customList)
//                showToastAndNavigate(getString(R.string.bookmark_removed))
//            }
//        }

        Log.d("DetailCustomizationActivity", "ID: $id")
        Log.d("DetailCustomizationActivity", "title: $namaBatik")
        Log.d("DetailCustomizationActivity", "imageurl: $imageUrl")
    }

    private fun showToastAndNavigate(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        // Navigate to BookmarkCustomizationFragment
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun back(){
        super.onBackPressed()
    }
}