package com.rbs.userscompose.network

import com.rbs.userscompose.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}