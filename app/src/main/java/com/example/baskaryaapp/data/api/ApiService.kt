package com.example.baskaryaapp.data.api

import com.example.baskaryaapp.data.response.ArticlesResponse
import com.example.baskaryaapp.data.response.BatikResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart

interface ApiService {

//    @Multipart
//    @POST("/")
//    suspend fun recommendation(
//        @Part file: MultipartBody.Part
//    ): Response<CameraResponse>

    @GET("/api/articles")
    suspend fun article(
    ): ArticlesResponse

    @GET("/api/batik")
    suspend fun batik(
    ): BatikResponse


}