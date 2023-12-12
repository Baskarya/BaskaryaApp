package com.example.baskaryaapp.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baskaryaapp.R
import com.example.baskaryaapp.databinding.ActivityLoginBinding
import com.example.baskaryaapp.databinding.ActivityMainBinding
import com.example.baskaryaapp.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

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
