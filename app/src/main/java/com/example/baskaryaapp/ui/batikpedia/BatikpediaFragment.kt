package com.example.baskaryaapp.ui.batikpedia

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baskaryaapp.data.api.ApiConfig.apiService
import com.example.baskaryaapp.data.helper.FirebaseHelper
import com.example.baskaryaapp.data.repo.BatikRepository
import com.example.baskaryaapp.data.response.BatikItem
import com.example.baskaryaapp.databinding.FragmentBatikpediaBinding
import com.example.baskaryaapp.ui.BatikViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class BatikpediaFragment : Fragment() {
    private lateinit var binding: FragmentBatikpediaBinding
    private val firebaseHelper = FirebaseHelper()
    private lateinit var batikList: MutableList<BatikItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentBatikpediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = BatikRepository.getInstance(apiService)
        val factory = BatikViewModelFactory.getInstance(repository)
        val batikpediaViewModel = ViewModelProvider(this, factory)[BatikpediaViewModel::class.java]

        val layoutManager = GridLayoutManager(requireContext(), 3)

        binding.idRVBatik.layoutManager = layoutManager

//        batikpediaViewModel.listBatik.observe(requireActivity()) { listBatik ->
//            setBatikData(listBatik)
//        }

        batikList = mutableListOf()

        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId != null) {
            // Dapatkan data bookmark
            firebaseHelper.getBookmarkedBatiks(userId) { bookmarkedIds ->
                // Set data batik
                batikpediaViewModel.listBatik.observe(requireActivity()) { listBatik ->
                    setBatikData(listBatik, bookmarkedIds)
                }
            }
        }

        lifecycleScope.launch {
            while (lifecycleScope.coroutineContext.isActive) {
                delay(10000)
                if (isAdded() && userId != null) {
                    // Dapatkan data bookmark
                    firebaseHelper.getBookmarkedBatiks(userId) { bookmarkedIds ->
                        if (isAdded()) {
                            // Set data batik
                            batikpediaViewModel.listBatik.observe(requireActivity()) { listBatik ->
                                setBatikData(listBatik, bookmarkedIds)
                            }
                        }
                    }
                }
            }
        }

        batikpediaViewModel.isLoading.observe(requireActivity()) { loading ->
            showLoading(loading)
        }
    }

//    private fun setBatikData(items: List<BatikItem>) {
//        val adapter = BatikRVAdapter()
//        adapter.submitList(items)
//        binding.idRVBatik.adapter = adapter
//        Log.d("DetailBatikActivity", "ID: $items")
//    }

    private fun setBatikData(items: List<BatikItem>, bookmarkedIds: List<String?>) {
        val adapter = BatikRVAdapter()
        // Set status bookmark pada setiap item berdasarkan daftar bookmarkedIds
        val itemsWithBookmarkStatus = items.map { batik ->
            batik.copy(isBookmarked = bookmarkedIds.contains(batik.id))
        }
        adapter.submitList(itemsWithBookmarkStatus)
        binding.idRVBatik.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}