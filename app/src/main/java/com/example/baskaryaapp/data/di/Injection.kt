package com.example.baskaryaapp.data.di

import android.content.Context
import com.example.baskaryaapp.data.api.ApiConfig
import com.example.baskaryaapp.data.database.BookmarkBatikDatabase
import com.example.baskaryaapp.data.repo.BatikRepository
import com.example.baskaryaapp.data.repo.BookmarkBatikRepository
import com.example.baskaryaapp.ui.BookmarkBatikViewModelFactory

object Injection {
    fun provideBookmarkBatikRepository(context: Context): BookmarkBatikRepository {
        val apiService = ApiConfig.apiService
        val database = BookmarkBatikDatabase.getInstance(context)
        val dao = database.bookmarkBatikDao()
        return BookmarkBatikRepository.getInstance(apiService, dao)
    }
}