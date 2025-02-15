package com.rbs.userscompose.ui.screen

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rbs.userscompose.model.User
import com.rbs.userscompose.ui.withBundle

@Composable
fun ProfileUserScreen(navController: NavController, user: User?) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color(0xfff8ccb4))
            ) {}

            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(user?.username ?: "Unknown User", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(user?.website.orEmpty(), Modifier.padding(top = 8.dp))
            }
        }

        CustomSpacer(16.dp)

        Box(
            modifier = Modifier
                .background(Color(0xffdedfe1), shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    val bundle = Bundle().apply {
                        putParcelable("user", user)
                    }
                    navController.withBundle(route = "personal-info", bundle)
                }) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "",
                        tint = Color(0xfff8ccb4)
                    )
                }
                Text(
                    "Personal Info", modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "",
                )
            }
        }

        CustomSpacer(16.dp)

        Box(
            modifier = Modifier
                .background(Color(0xffdedfe1), shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "",
                        tint = Color(0xffec686f)
                    )
                }
                Text(
                    "Log Out", modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "",
                )
            }
        }
    }
}