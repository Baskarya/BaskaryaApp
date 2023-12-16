package com.example.baskaryaapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.api.ApiConfig
import com.example.baskaryaapp.data.repo.ArticlesRepository
import com.example.baskaryaapp.data.response.ArticlesItem
import com.example.baskaryaapp.databinding.FragmentSearchArticleBinding
import com.example.baskaryaapp.ui.ArticlesViewModelFactory
import com.example.baskaryaapp.ui.articles.ArticlesAdapter
import com.example.baskaryaapp.ui.articles.ArticlesViewModel

class SearchArticleFragment : Fragment() {

    private lateinit var binding: FragmentSearchArticleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchArticleBinding.inflate(inflater, container, false)
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
        val searchView =view.findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    // Buat instance dari SearchResultFragment
                    val searchResultFragment = SearchResultFragment()

                    // Lakukan transaksi fragment untuk pindah ke SearchResultFragment
                    val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.homefragment, searchResultFragment)
                    fragmentTransaction.addToBackStack(null) // Agar dapat kembali ke fragment sebelumnya
                    fragmentTransaction.commit()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchResultFragment = SearchResultFragment()

                if (!newText.isNullOrEmpty()) {
                    // Buat instance SearchResultFragment dengan membawa data pencarian
                    val bundle = Bundle()
                    bundle.putString("query", newText)
                    searchResultFragment.arguments = bundle
                }

                return true
            }
        })
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