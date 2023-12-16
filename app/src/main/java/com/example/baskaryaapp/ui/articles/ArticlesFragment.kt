package com.example.baskaryaapp.ui.articles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baskaryaapp.data.api.ApiConfig
import com.example.baskaryaapp.data.repo.ArticlesRepository
import com.example.baskaryaapp.data.response.ArticlesItem
import com.example.baskaryaapp.databinding.FragmentArticlesBinding
import com.example.baskaryaapp.ui.ArticlesViewModelFactory

class ArticlesFragment : Fragment() {

    private lateinit var binding: FragmentArticlesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = ArticlesRepository.getInstance(ApiConfig.apiService)
        val factory = ArticlesViewModelFactory.getInstance(repository)
        val articlesViewModel = ViewModelProvider(this, factory)[ArticlesViewModel::class.java]

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvArticles.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvArticles.addItemDecoration(itemDecoration)

        articlesViewModel.listArticles.observe(requireActivity()) { listArticles ->
            setArticlesData(listArticles)
        }

        articlesViewModel.isLoading.observe(requireActivity()) { loading ->
            showLoading(loading)
        }
    }

    private fun setArticlesData(items: List<ArticlesItem>) {
        val adapter = ArticlesAdapter()
        adapter.submitList(items)
        binding.rvArticles.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}