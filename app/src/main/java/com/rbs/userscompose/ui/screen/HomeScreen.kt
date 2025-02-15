package com.rbs.userscompose.ui.screen

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rbs.userscompose.viewmodel.UsersViewModel
import com.rbs.userscompose.network.ResultState
import com.rbs.userscompose.model.User
import com.rbs.userscompose.ui.withBundle

@Composable
fun HomeScreen(navController: NavController, viewModel: UsersViewModel = hiltViewModel()) {
    val usersState by viewModel.usersState.collectAsState()

    when (val state = usersState) {
        is ResultState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ResultState.Success -> {
            SetData(navController, state.data)
        }

        is ResultState.Error -> Text(text = "Error: ${state.message}")
    }
}

@Composable
fun SetData(navController: NavController, data: List<User>) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Users List",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(data) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            val bundle = Bundle().apply {
                                putParcelable("user", user)
                            }
                            navController.withBundle(route = "profile-user", bundle)
                        }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = user.username, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}