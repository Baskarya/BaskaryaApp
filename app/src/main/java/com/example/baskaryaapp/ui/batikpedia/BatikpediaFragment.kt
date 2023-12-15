package com.example.baskaryaapp.ui.batikpedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baskaryaapp.data.api.ApiConfig.apiService
import com.example.baskaryaapp.data.repo.BatikRepository
import com.example.baskaryaapp.data.response.BatikItem
import com.example.baskaryaapp.databinding.FragmentBatikpediaBinding
import com.example.baskaryaapp.ui.BatikViewModelFactory

class BatikpediaFragment : Fragment() {
    private lateinit var binding: FragmentBatikpediaBinding

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

        // on below line we are creating a variable
        // for our grid layout manager and specifying
        // column count as 2
        val layoutManager = GridLayoutManager(requireContext(), 3)

        binding.idRVBatik.layoutManager = layoutManager

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
        binding.idRVBatik.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}