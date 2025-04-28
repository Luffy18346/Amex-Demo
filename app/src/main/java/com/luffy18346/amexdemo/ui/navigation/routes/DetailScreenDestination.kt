package com.luffy18346.amexdemo.ui.navigation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.luffy18346.amexdemo.ui.feature.detail.DetailContract
import com.luffy18346.amexdemo.ui.feature.detail.DetailScreen
import com.luffy18346.amexdemo.ui.feature.main.MainViewModel
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.PICTURE_ID
import com.luffy18346.amexdemo.ui.navigation.Navigation.Routes.PICTURES_PARENT
import com.luffy18346.amexdemo.ui.navigation.Navigation.Routes.PICTURE_DETAILS
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.detailScreenRoute(navController: NavController) {
    composable(route = PICTURE_DETAILS, arguments = listOf(navArgument(PICTURE_ID) {
        type = NavType.LongType
    })) {
        val pictureId = requireNotNull(it.arguments?.getLong(PICTURE_ID)) { "User id is required as an argument" }

        // create backstack entry from parent route which was passed in NavHost
        val backStackEntry = remember(it) { navController.getBackStackEntry(PICTURES_PARENT) }

        // pass the backstack entry as viewModelStoreOwner
        val viewModel: MainViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)
        DetailScreenDestination(pictureId, viewModel, navController)
    }
}

@Composable
fun DetailScreenDestination(pictureId: Long, viewModel: MainViewModel, navController: NavController) {
    DetailScreen(
        picture = viewModel.getPictureById(pictureId),
        effectFlow = viewModel.effect,
        onEventSent = { event ->  viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is DetailContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        },
    )
}
