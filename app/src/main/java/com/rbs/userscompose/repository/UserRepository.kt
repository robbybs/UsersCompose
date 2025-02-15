package com.rbs.userscompose.repository

import com.rbs.userscompose.network.ApiService
import com.rbs.userscompose.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) { apiService.getUsers() }
}