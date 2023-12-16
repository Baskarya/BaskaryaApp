package com.example.baskaryaapp.data.repo

import androidx.lifecycle.LiveData
import com.example.baskaryaapp.data.api.ApiService
import com.example.baskaryaapp.data.database.BookmarkBatik
import com.example.baskaryaapp.data.database.BookmarkBatikDao
import com.example.baskaryaapp.data.response.BatikResponse

class BookmarkBatikRepository private constructor(
    private val apiService: ApiService,
    private val bookmarkBatikDao: BookmarkBatikDao,
){

    suspend fun batik(): BatikResponse {
        return apiService.batik()
    }

    fun getAllBookmarkBatik(): LiveData<List<BookmarkBatik>> {
        return bookmarkBatikDao.getAllBookmarkBatik()
    }

    suspend fun setFavoriteUser(bookmarkBatik: BookmarkBatik) {
        bookmarkBatikDao.insert(bookmarkBatik)
    }

    suspend fun deleteFavoriteUser(id: Int) {
        bookmarkBatikDao.delete(id)
    }

    suspend fun isBookmarked(id: Int) = bookmarkBatikDao.getBookmarkBatikByTitle(id)

    companion object {
        @Volatile
        private var instance: BookmarkBatikRepository? = null
        fun getInstance(
            apiService: ApiService,
            batikDao: BookmarkBatikDao
        ): BookmarkBatikRepository =
            instance ?: synchronized(this) {
                instance ?: BookmarkBatikRepository(apiService, batikDao)
            }.also { instance = it }
    }
}