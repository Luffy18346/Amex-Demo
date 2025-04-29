package com.luffy18346.amexdemo.ui.navigation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.luffy18346.amexdemo.ui.feature.detail.DetailContract
import com.luffy18346.amexdemo.ui.feature.detail.DetailScreen
import com.luffy18346.amexdemo.ui.feature.detail.DetailViewModel
import com.luffy18346.amexdemo.ui.navigation.NavigationRoutes.PictureDetails
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.detailScreenRoute(navController: NavController) {
    composable<PictureDetails> { backStackEntry ->
        val pictureDetails: PictureDetails = backStackEntry.toRoute()
        val viewModel: DetailViewModel = koinViewModel<DetailViewModel>()

        DetailScreenDestination(
            pictureDetails,
            viewModel,
            navController
        )
    }
}

@Composable
fun DetailScreenDestination(
    pictureDetails: PictureDetails,
    viewModel: DetailViewModel,
    navController: NavController
) {
    DetailScreen(
        pictureDetails = pictureDetails,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is DetailContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        },
    )
}
