package com.rbs.userscompose.viewmodel

import com.rbs.userscompose.model.User
import com.rbs.userscompose.network.ApiService
import com.rbs.userscompose.network.ResultState
import com.rbs.userscompose.repository.UserRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: UsersViewModel
    private lateinit var mockRepository: UserRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockRepository = mock(UserRepository::class.java)
        viewModel = UsersViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test fetchUsers success`() = runTest {
        val expectedUsers = listOf(
            User(
                1,
                "John Doe",
                "johndoe",
                "johndoe@example.com",
                "12345678",
                "johndoe.com"
            ),
            User(
                2,
                "Jane Doe",
                "janedoe",
                "janedoe@example.com",
                "12345678",
                "janedoe.com"
            )
        )

        `when`(mockRepository.getUsers()).thenReturn(expectedUsers)

        viewModel.fetchUsers()

        advanceUntilIdle()

        assertEquals(ResultState.Success(expectedUsers), viewModel.usersState.value)
    }

    @Test
    fun `test fetchUsers failure`() = runTest {
        val errorMessage = "Network Error"

        `when`(mockRepository.getUsers()).thenThrow(RuntimeException(errorMessage))

        viewModel.fetchUsers()

        advanceUntilIdle()

        assertEquals(ResultState.Error(errorMessage), viewModel.usersState.value)
    }
}