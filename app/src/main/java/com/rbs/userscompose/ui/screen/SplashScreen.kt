package com.rbs.userscompose.ui.screen

import android.R.drawable
import android.R.id
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.rbs.userscompose.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigation: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")
    }

    LaunchedEffect(Unit) {
        delay(500)
        onNavigation()
    }
}