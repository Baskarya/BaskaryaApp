package com.example.baskaryaapp.ui.setting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.baskaryaapp.R
import com.example.baskaryaapp.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SettingFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth
    private val pref by lazy { Prefference(requireContext()) }

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
        val currentUser = auth.currentUser
        currentUser?.let {
            val userEmail = it.email
            view.findViewById<TextView>(R.id.tv_email)?.text = userEmail
        }

        view.findViewById<View>(R.id.btn_logout)?.setOnClickListener {
            showLogoutConfirmation()
        }
        //ganti bahasa
        view.findViewById<View>(R.id.cv_bahasa).setOnClickListener{
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            true
        }

        val switchTheme = view.findViewById<Switch>(R.id.sw_mode)
        switchTheme.isChecked = pref.getBoolean("dark_mode")

        switchTheme.setOnCheckedChangeListener{compoundButton,isChacked->
            when(isChacked){
                true->{
                    pref.put("dark_mode",true)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                }
                false->{
                    pref.put("dark_mode",false)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                }
            }
        }
    }

    private fun showLogoutConfirmation() {
        // Menampilkan dialog konfirmasi sebelum logout
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.konfirmasi_logout)
            .setMessage(R.string.yakin_logout)
            .setPositiveButton(R.string.Ya) { _, _ ->
                logoutUser()
            }
            .setNegativeButton(R.string.No, null)
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