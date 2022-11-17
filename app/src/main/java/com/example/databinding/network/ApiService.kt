package com.example.databinding.network

import com.example.databinding.model.RepositoryDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("repositories")
    fun getAllRepository(
        @Query("q") query: String
    ): Call<RepositoryDTO>
}