package com.rbs.userscompose.ui

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rbs.userscompose.model.User
import com.rbs.userscompose.ui.screen.HomeScreen
import com.rbs.userscompose.ui.screen.LoginScreen
import com.rbs.userscompose.ui.screen.PersonalInfoScreen
import com.rbs.userscompose.ui.screen.ProfileUserScreen
import com.rbs.userscompose.ui.screen.SplashScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("profile-user") { backStackEntry ->
            val user = backStackEntry.arguments?.getParcelable("user", User::class.java)
            ProfileUserScreen(navController, user = user)
        }
        composable("personal-info") { backStackEntry ->
            val user = backStackEntry.arguments?.getParcelable("user", User::class.java)
            PersonalInfoScreen(navController, user = user)
        }
    }
}

fun NavController.withBundle(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}