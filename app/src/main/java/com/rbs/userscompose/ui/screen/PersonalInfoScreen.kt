package com.rbs.userscompose.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocalPhone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rbs.userscompose.model.User

@Composable
fun PersonalInfoScreen(navController: NavController, user: User?) {
    val context = LocalContext.current

    Column(modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color(0xffdedfe1))
                    .clickable {
                        navController.navigateUp()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = ""
                )
            }

            Text(
                "Personal Info", modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "EDIT", modifier = Modifier
                    .clickable {
                        Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                    },
                color = Color(0xfff8ccb4),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        CustomSpacer(16.dp)

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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xffdedfe1), shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
        ) {
            Row(
                modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable {
                            navController.navigateUp()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "",
                        tint = Color(0xfff8ccb4)
                    )
                }
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text("FULL NAME")
                    Text(user?.name.orEmpty(), fontSize = 12.sp)
                }
            }

            Row(
                modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable {
                            navController.navigateUp()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "",
                        tint = Color(0xff5954f4)
                    )
                }
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text("EMAIL")
                    Text(user?.email.orEmpty().lowercase(), fontSize = 12.sp)
                }
            }

            Row(
                modifier = Modifier.padding(top = 8.dp, start = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable {
                            navController.navigateUp()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = Icons.Outlined.LocalPhone,
                        contentDescription = "",
                        tint = Color(0xff6ba7fc)
                    )
                }
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text("PHONE NUMBER")
                    Text(user?.phone.orEmpty(), fontSize = 12.sp)
                }
            }
        }
    }
}