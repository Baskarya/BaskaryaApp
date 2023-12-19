package com.example.baskaryaapp.ui.bookmark.batikpedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.api.ApiConfig
import com.example.baskaryaapp.data.helper.FirebaseHelper
import com.example.baskaryaapp.data.repo.BatikRepository
import com.example.baskaryaapp.data.response.BatikItem
import com.example.baskaryaapp.databinding.FragmentBatikpediaBinding
import com.example.baskaryaapp.databinding.FragmentBookmarkBatikBinding
import com.example.baskaryaapp.ui.BatikViewModelFactory
import com.example.baskaryaapp.ui.batikpedia.BatikRVAdapter
import com.example.baskaryaapp.ui.batikpedia.BatikpediaViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class BookmarkBatikFragment : Fragment() {
    private lateinit var binding: FragmentBookmarkBatikBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentBookmarkBatikBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val firebaseHelper = FirebaseHelper()
    private lateinit var batikList: MutableList<BatikItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = BatikRepository.getInstance(ApiConfig.apiService)
        val factory = BatikViewModelFactory.getInstance(repository)
        val batikpediaViewModel = ViewModelProvider(this, factory)[BatikpediaViewModel::class.java]

        val layoutManager = GridLayoutManager(requireContext(), 3)
        binding.idBookmarkBatik.layoutManager = layoutManager

//        batikpediaViewModel.listBatik.observe(requireActivity()) { listBatik ->
//            setBatikData(listBatik)
//        }

        batikList = mutableListOf()

        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

//        if (userId != null) {
//            // Dapatkan data bookmark
//            firebaseHelper.getBookmarkedBatiks(userId) { bookmarkedIds ->
//                // Set data batik
//                batikpediaViewModel.listBatik.observe(requireActivity()) { listBatik ->
//                    setBatikData(listBatik, bookmarkedIds)
////                    setBatikData2(listBatik, bookmarkedIds)
//                }
//            }
//        }

        lifecycleScope.launch {
            while (lifecycleScope.coroutineContext.isActive) {
                delay(5000)
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

    private fun setBatikData(items: List<BatikItem>, bookmarkedIds: List<String?>) {
        val adapter = BatikRVAdapter()

        // Filter batik yang telah di-bookmark berdasarkan daftar bookmarkedIds
        val bookmarkedBatiks = items.filter { batik ->
            bookmarkedIds.contains(batik.id)
        }

        // Set status bookmark pada semua batik
        val itemsWithBookmarkStatus = bookmarkedBatiks.map { batik ->
            batik.copy(isBookmarked = bookmarkedIds.contains(batik.id))
        }
        adapter.submitList(itemsWithBookmarkStatus)

        binding.idBookmarkBatik.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}