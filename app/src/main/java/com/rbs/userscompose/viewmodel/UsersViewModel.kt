package com.rbs.userscompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbs.userscompose.network.ResultState
import com.rbs.userscompose.model.User
import com.rbs.userscompose.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _usersState = MutableStateFlow<ResultState<List<User>>>(ResultState.Loading)
    val usersState: StateFlow<ResultState<List<User>>> = _usersState

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _usersState.value = ResultState.Loading
            try {
                val users = repository.getUsers()
                _usersState.emit(ResultState.Success(users))
            } catch (e: Exception) {
                _usersState.emit(ResultState.Error(e.message ?: "Unknown error"))
            }
        }
    }
}