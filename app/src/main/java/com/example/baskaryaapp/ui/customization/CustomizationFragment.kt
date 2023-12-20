package com.example.baskaryaapp.ui.customization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.baskaryaapp.databinding.FragmentCustomizationBinding


class CustomizationFragment : Fragment() {

    private lateinit var binding: FragmentCustomizationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString("url") // Mendapatkan URL dari argument
        val namaBatik = arguments?.getString("namaBatik") // Mendapatkan namaBatik dari argument

        // Menampilkan data ke dalam ImageView dan TextView
        url?.let {
            Glide.with(requireContext())
                .load(it)
                .into(binding.ivCustom) // Menampilkan URL ke dalam ImageView
        }

        binding.tvPattern.text = namaBatik // Menampilkan namaBatik ke dalam TextView
    }
    companion object {
        private const val ARG_URL = "arg_url"
        private const val ARG_NAMA_BATIK = "arg_nama_batik"

        @JvmStatic
        fun newInstance(url: String, namaBatik: String): CustomizationFragment {
            val fragment = CustomizationFragment()
            val args = Bundle()
            args.putString(ARG_URL, url)
            args.putString(ARG_NAMA_BATIK, namaBatik)
            fragment.arguments = args
            return fragment
        }
    }
}
