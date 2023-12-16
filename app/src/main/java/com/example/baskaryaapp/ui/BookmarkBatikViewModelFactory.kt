package com.example.baskaryaapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.baskaryaapp.data.di.Injection
import com.example.baskaryaapp.data.repo.BookmarkBatikRepository
import com.example.baskaryaapp.ui.detailBatik.DetailBatikViewModel

class BookmarkBatikViewModelFactory private constructor(private val bookmarkBatikRepository: BookmarkBatikRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: BookmarkBatikViewModelFactory? = null
        fun getInstance(context: Context): BookmarkBatikViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: BookmarkBatikViewModelFactory(Injection.provideBookmarkBatikRepository(context))
            }.also { instance = it }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailBatikViewModel::class.java)) {
            return DetailBatikViewModel(bookmarkBatikRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}