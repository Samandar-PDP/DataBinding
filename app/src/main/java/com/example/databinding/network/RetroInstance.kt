package com.example.databinding.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetroInstance {
    const val BASE_URL = "https://api.github.com/search/"

    private fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ApiService = getRetroInstance().create(ApiService::class.java)
}