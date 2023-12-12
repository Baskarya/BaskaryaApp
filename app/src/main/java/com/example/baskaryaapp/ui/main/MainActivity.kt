package com.example.baskaryaapp.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.content.ActivityNotFoundException
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.baskaryaapp.R
import com.example.baskaryaapp.databinding.ActivityMainBinding
import com.example.baskaryaapp.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var navview: BottomNavigationView
    val REQUEST_IMAGE_CAPTURE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)

        checkUserSession()

        binding.logout.setOnClickListener {
            logoutUser()
        }

        binding.fab.setOnClickListener{
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
            }catch (e:ActivityNotFoundException){
                Toast.makeText(this,"Error : "+ e.localizedMessage,Toast.LENGTH_SHORT).show()
            }
        }

        navview =findViewById(R.id.bottomNavigationView)
//        replace(HomeFragment())
//        navview.setOnItemSelectedListener{
//            when(it.itemId){
//                R.id.home->replace(HomeFragment())
//                R.id.article->replace(HomeFragment())//Article Fragment
//                R.id.bookmark->replace(HomeFragment())//Bookmark Fragment
//                R.id.Settings->replace(SettingFragment())
//            }
//            true
//        }

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

    private fun logoutUser() {
        auth.signOut()

        // Clear user session
        clearUserSession()

        // Redirect to LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun clearUserSession() {
        // Mark the user as logged out
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
    }

    private fun checkUserSession() {
        // Check if the user is still logged in
        if (!sharedPreferences.getBoolean("isLoggedIn", false)) {
            // If not logged in, redirect to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}