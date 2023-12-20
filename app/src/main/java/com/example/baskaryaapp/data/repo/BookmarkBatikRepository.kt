package com.example.baskaryaapp.data.repo

import androidx.lifecycle.LiveData
import com.example.baskaryaapp.data.api.ApiService
import com.example.baskaryaapp.data.response.BatikResponse

class BookmarkBatikRepository private constructor(
    private val apiService: ApiService,
){

    suspend fun batik(): BatikResponse {
        return apiService.batik()
    }

    companion object {
        @Volatile
        private var instance: BookmarkBatikRepository? = null
        fun getInstance(
            apiService: ApiService,
        ): BookmarkBatikRepository =
            instance ?: synchronized(this) {
                instance ?: BookmarkBatikRepository(apiService)
            }.also { instance = it }
    }
}