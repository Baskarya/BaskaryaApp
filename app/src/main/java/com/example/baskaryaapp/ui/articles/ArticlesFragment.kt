package com.example.baskaryaapp.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baskaryaapp.data.api.ApiConfig
import com.example.baskaryaapp.data.helper.FirebaseHelper
import com.example.baskaryaapp.data.repo.ArticlesRepository
import com.example.baskaryaapp.data.response.ArticlesItem
import com.example.baskaryaapp.databinding.FragmentArticlesBinding
import com.example.baskaryaapp.ui.ArticlesViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ArticlesFragment : Fragment() {

    private lateinit var binding: FragmentArticlesBinding
    private val firebaseHelper = FirebaseHelper()
    private lateinit var articleList: MutableList<ArticlesItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        articleList = mutableListOf()

        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

//        articlesViewModel.listArticles.observe(requireActivity()) { listArticles ->
//            setArticlesData(listArticles)
//        }

        lifecycleScope.launch {
            while (lifecycleScope.coroutineContext.isActive) {
                showLoading(true)
                delay(5000)
                if (isAdded() && userId != null) {
                    // Dapatkan data bookmark
                    firebaseHelper.getBookmarkedArticles(userId) { bookmarkedIds ->
                        if (isAdded()) {
                            // Set data article
                            articlesViewModel.listArticles.observe(requireActivity()) { listArticles ->
                                setArticlesData(listArticles, bookmarkedIds)
                            }

//                            articlesViewModel.isLoading.observe(requireActivity()) { loading ->
//                                showLoading(loading)
//                            }
                            showLoading(false)
                        }
                    }
                }
            }
        }


    }

//    private fun setArticlesData(items: List<ArticlesItem>) {
//        val adapter = ArticlesAdapter()
//        adapter.submitList(items)
//        binding.rvArticles.adapter = adapter
//    }

    private fun setArticlesData(items: List<ArticlesItem>, bookmarkedIds: List<String?>) {
        val adapter = ArticlesAdapter()
        // Set status bookmark pada setiap item berdasarkan daftar bookmarkedIds
        val itemsWithBookmarkStatus = items.map { batik ->
            batik.copy(isBookmarked = bookmarkedIds.contains(batik.id))
        }
        adapter.submitList(itemsWithBookmarkStatus)
        binding.rvArticles.adapter = adapter

        showLoading(false)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}