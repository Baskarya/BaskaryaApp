package com.example.baskaryaapp.ui.bookmark.customization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baskaryaapp.data.api.ApiConfig
import com.example.baskaryaapp.data.database.BookmarkCustom
import com.example.baskaryaapp.data.helper.FirebaseHelper
import com.example.baskaryaapp.data.repo.ArticlesRepository
import com.example.baskaryaapp.data.response.ArticlesItem
import com.example.baskaryaapp.data.response.BatikItem
import com.example.baskaryaapp.data.response.Data
import com.example.baskaryaapp.databinding.FragmentBookmarkCustomizationBinding
import com.example.baskaryaapp.ui.ArticlesViewModelFactory
import com.example.baskaryaapp.ui.articles.ArticlesViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class BookmarkCustomizationFragment : Fragment(){

private lateinit var binding: FragmentBookmarkCustomizationBinding
    private val firebaseHelper = FirebaseHelper()
    private lateinit var customList: MutableList<Data>
    private lateinit var bookmarkedCustomizations: MutableList<BookmarkCustom>

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    // Inflate the layout for this fragment
    binding = FragmentBookmarkCustomizationBinding.inflate(inflater, container, false)
    return binding.root
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

//    val repository = ArticlesRepository.getInstance(ApiConfig.apiService)
//    val factory = ArticlesViewModelFactory.getInstance(repository)
//    val articlesViewModel = ViewModelProvider(this, factory)[ArticlesViewModel::class.java]

    val layoutManager = LinearLayoutManager(requireActivity())
    binding.rvBookmarkCustom.layoutManager = layoutManager
    val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
    binding.rvBookmarkCustom.addItemDecoration(itemDecoration)

    customList = mutableListOf()

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val userId = auth.currentUser?.uid

//    articlesViewModel.listArticles.observe(requireActivity()) { listArticles ->
//        setCustomizationData(listArticles)
//    }

//    if (userId != null) {
//        firebaseHelper.getBookmarkedCustom(userId) { bookmarkedIds ->
//            fetchCustomizations(bookmarkedIds) { customizations ->
//                setCustomizationData(customizations)
//            }
//        }
//    }

    userId?.let { uid ->
        firebaseHelper.fetchCustomizations(uid) { customizations ->
            bookmarkedCustomizations = customizations.toMutableList()
            setCustomizationData(bookmarkedCustomizations)
        }
    }

//    lifecycleScope.launch {
//        while (lifecycleScope.coroutineContext.isActive) {
//            delay(5000)
//            if (isAdded() && userId != null) {
//                // Dapatkan data bookmark
//                firebaseHelper.getBookmarkedBatiks(userId) { bookmarkedIds ->
//                    if (isAdded()) {
//                        // Set data batik
//                        batikpediaViewModel.listBatik.observe(requireActivity()) { listBatik ->
//                            setBatikData(listBatik, bookmarkedIds)
//                        }
//                    }
//                }
//            }
//        }
//    }

//    articlesViewModel.isLoading.observe(requireActivity()) { loading ->
//        showLoading(loading)
//    }
}

//private fun setCustomizationData(items: List<Data>) {
//    val adapter = CustomizationAdapter()
//    adapter.submitList(items)
//    binding.rvBookmarkCustom.adapter = adapter
//}

//    private fun fetchCustomizations(bookmarkedIds: List<String?>, callback: (List<Data>) -> Unit) {
//        firebaseHelper.fetchCustomizations(bookmarkedIds, callback)
//    }
//
//    private fun setCustomizationData(items: List<Data>) {
//        val adapter = CustomizationAdapter()
//        adapter.submitList(items)
//        binding.rvBookmarkCustom.adapter = adapter
//    }

    private fun setCustomizationData(items: List<BookmarkCustom>) {
        val adapter = CustomizationAdapter()
        adapter.submitList(items)
        binding.rvBookmarkCustom.adapter = adapter
    }

private fun showLoading(isLoading: Boolean) {
    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
}
}