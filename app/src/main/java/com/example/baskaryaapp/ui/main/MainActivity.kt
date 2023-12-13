package com.example.baskaryaapp.ui.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.baskaryaapp.R
import com.example.baskaryaapp.databinding.ActivityMainBinding
import com.example.baskaryaapp.ui.home.HomeFragment
import com.example.baskaryaapp.ui.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navview: BottomNavigationView
    private lateinit var binding: ActivityMainBinding
    val REQUEST_IMAGE_CAPTURE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    binding.fab.setOnClickListener{
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
        }catch (e:ActivityNotFoundException){
            Toast.makeText(this,"Error : "+ e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }
        navview =findViewById(R.id.bottomNavigationView)
        replace(HomeFragment())
        navview.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home->replace(HomeFragment())
                R.id.article->replace(HomeFragment())//Article Fragment
                R.id.bookmark->replace(HomeFragment())//Bookmark Fragment
                R.id.Settings->replace(SettingFragment())
            }
            true
        }


    }
    private fun replace (fragment : Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.navhost,fragment)
        fragmentTransaction.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode== RESULT_OK){
            val imageBitmap=data?.extras?.get("data")as Bitmap
        }else{super.onActivityResult(requestCode, resultCode, data)

        }
    }
}