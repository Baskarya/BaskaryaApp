package com.example.baskaryaapp.ui.bookmark.batikpedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baskaryaapp.data.database.BookmarkBatik
import com.example.baskaryaapp.data.repo.BatikRepository
import com.example.baskaryaapp.data.repo.BookmarkBatikRepository

class BookmarkBatikViewModel(private val bookmarkBatikRepository: BookmarkBatikRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllBookmarkBatik(): LiveData<List<BookmarkBatik>> = bookmarkBatikRepository.getAllBookmarkBatik()

    companion object{
        private const val TAG = "BookmarkBatikViewModel"
    }
}