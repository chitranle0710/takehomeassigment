package com.example.tymextakehomeapp.data.remote

import com.example.tymextakehomeapp.data.remote.dto.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getGithubUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int = 20
    ): List<User>

    @GET("users/{id}")
    suspend fun getUserDetail(@Path("id") id: Int): User
}