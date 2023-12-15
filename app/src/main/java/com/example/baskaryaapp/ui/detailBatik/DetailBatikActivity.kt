package com.example.baskaryaapp.ui.detailBatik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.response.DataItem
import com.example.baskaryaapp.databinding.ActivityDetailBatikBinding

class DetailBatikActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBatikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBatikBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val batik = intent.getParcelableExtra<DataItem>(EXTRA_BATIK)
        batik?.let {
            Glide.with(this)
                .load(it.imageUrl)
                .into(binding.imgItemPhoto)
            binding.tvItemTitle.text = it.title
            binding.tvItemSubTitle.text = it.origin
            binding.tvItemDescription.text = it.description
        }
    }

    companion object {
        const val EXTRA_BATIK = "key_batik"
    }
}