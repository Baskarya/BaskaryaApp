package com.example.baskaryaapp.ui.setting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.baskaryaapp.R
import com.example.baskaryaapp.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SettingFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inisialisasi Firebase Authentication dan SharedPreferences
        auth = FirebaseAuth.getInstance()
        sharedPreferences = requireActivity().getSharedPreferences("user_session", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Implementasi logout ketika tombol logout diklik
        view.findViewById<View>(R.id.btn_logout)?.setOnClickListener {
            showLogoutConfirmation()
        }

        val switchTheme = view.findViewById<Switch>(R.id.sw_mode)

        // Mendeteksi perubahan pada Switch
        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Ganti ke Dark Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                saveThemePreference(true) // Simpan preferensi Dark Mode
            } else {
                // Ganti ke Light Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                saveThemePreference(false) // Simpan preferensi Light Mode
            }
        }
        if (getSavedThemePreference()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }


    private fun saveThemePreference(isDarkMode: Boolean) {
        sharedPreferences.edit().putBoolean("isDarkMode", isDarkMode).apply()
    }

    private fun getSavedThemePreference(): Boolean {
        return sharedPreferences.getBoolean("isDarkMode", false)
    }
    private fun showLogoutConfirmation() {
        // Menampilkan dialog konfirmasi sebelum logout
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->
                logoutUser()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun logoutUser() {
        auth.signOut()

        // Clear user session
        clearUserSession()

        // Redirect to LoginActivity
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun clearUserSession() {
        // Mark the user as logged out
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
    }
}