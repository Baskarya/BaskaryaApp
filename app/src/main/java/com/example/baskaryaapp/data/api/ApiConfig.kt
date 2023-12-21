package com.example.baskaryaapp.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://md-baskarya-veuznuhx2a-et.a.run.app/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://mobile-aqtiniby7q-et.a.run.app/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://baskarya-app.et.r.appspot.com/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://baskarya-app-veuznuhx2a-et.a.run.app/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://baskarya-app-veuznuhx2a-et.a.run.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        )
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}