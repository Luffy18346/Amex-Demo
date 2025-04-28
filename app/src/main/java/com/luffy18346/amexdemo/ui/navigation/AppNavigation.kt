package com.luffy18346.amexdemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.luffy18346.amexdemo.ui.navigation.Navigation.Routes.PICTURES
import com.luffy18346.amexdemo.ui.navigation.routes.detailScreenRoute
import com.luffy18346.amexdemo.ui.navigation.routes.mainScreenRoute


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PICTURES,
    ) {
        mainScreenRoute(navController = navController)
        detailScreenRoute(navController = navController)
    }
}

