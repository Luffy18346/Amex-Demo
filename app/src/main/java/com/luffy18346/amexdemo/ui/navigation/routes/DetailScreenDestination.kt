package com.luffy18346.amexdemo.ui.navigation.routes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.luffy18346.amexdemo.ui.feature.detail.DetailContract
import com.luffy18346.amexdemo.ui.feature.detail.DetailScreen
import com.luffy18346.amexdemo.ui.feature.detail.DetailViewModel
import com.luffy18346.amexdemo.ui.navigation.NavigationRoutes.PictureDetails
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.detailScreenRoute(navController: NavController) {
    composable<PictureDetails> { backStackEntry ->
        val viewModel: DetailViewModel = koinViewModel<DetailViewModel>()
        DetailScreenDestination(viewModel, navController)
    }
}

@Composable
fun DetailScreenDestination(
    viewModel: DetailViewModel,
    navController: NavController,
) {
    val state = viewModel.viewState.collectAsStateWithLifecycle()
    DetailScreen(
        state = state.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is DetailContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        },
    )
}
