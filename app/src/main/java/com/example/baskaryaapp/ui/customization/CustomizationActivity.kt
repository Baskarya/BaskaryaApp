package com.example.baskaryaapp.ui.customization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.baskaryaapp.databinding.ActivityCustomizationBinding

class CustomizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomizationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data yang dikirimkan dari RecomendationFragment
        val imageUrl = intent.getStringExtra("IMAGE_URL")
        val namaBatik = intent.getStringExtra("NAMA_BATIK")

        Glide.with(this)
            .load(imageUrl)
            .into(binding.ivCustom)
        binding.tvPattern.text = "$namaBatik Pattern"
    }
}