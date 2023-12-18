package com.example.baskaryaapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://mobile-aqtiniby7q-et.a.run.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}