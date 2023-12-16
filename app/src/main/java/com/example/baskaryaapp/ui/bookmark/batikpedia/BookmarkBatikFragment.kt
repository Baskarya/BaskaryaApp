package com.example.baskaryaapp.ui.bookmark.batikpedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.api.ApiConfig
import com.example.baskaryaapp.data.repo.BatikRepository
import com.example.baskaryaapp.data.response.BatikItem
import com.example.baskaryaapp.databinding.FragmentBatikpediaBinding
import com.example.baskaryaapp.databinding.FragmentBookmarkBatikBinding
import com.example.baskaryaapp.ui.BatikViewModelFactory
import com.example.baskaryaapp.ui.batikpedia.BatikRVAdapter
import com.example.baskaryaapp.ui.batikpedia.BatikpediaViewModel


class BookmarkBatikFragment : Fragment() {
    private lateinit var binding: FragmentBookmarkBatikBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentBookmarkBatikBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = BatikRepository.getInstance(ApiConfig.apiService)
        val factory = BatikViewModelFactory.getInstance(repository)
        val batikpediaViewModel = ViewModelProvider(this, factory)[BatikpediaViewModel::class.java]

        val layoutManager = GridLayoutManager(requireContext(), 3)
        binding.idBookmarkBatik.layoutManager = layoutManager

        batikpediaViewModel.listBatik.observe(requireActivity()) { listBatik ->
            setBatikData(listBatik)
        }

        batikpediaViewModel.isLoading.observe(requireActivity()) { loading ->
            showLoading(loading)
        }
    }

    private fun setBatikData(items: List<BatikItem>) {
        val adapter = BatikRVAdapter()
        adapter.submitList(items)
        binding.idBookmarkBatik.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}