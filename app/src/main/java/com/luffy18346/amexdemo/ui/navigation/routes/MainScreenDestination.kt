package com.luffy18346.amexdemo.ui.navigation.routes

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.luffy18346.amexdemo.ui.feature.main.MainContract
import com.luffy18346.amexdemo.ui.feature.main.MainScreen
import com.luffy18346.amexdemo.ui.feature.main.MainViewModel
import com.luffy18346.amexdemo.ui.navigation.Navigation.Routes.PICTURES
import com.luffy18346.amexdemo.ui.navigation.navigateToRepos
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.mainScreenRoute(navController: NavController) {
    composable(PICTURES) {
        val viewModel: MainViewModel = koinViewModel<MainViewModel>()
        MainScreenDestination(viewModel, navController)
    }
}

@Composable
fun MainScreenDestination(viewModel: MainViewModel, navController: NavController) {
    val state = viewModel.viewState.collectAsStateWithLifecycle()
    MainScreen(
        state = state.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is MainContract.Effect.Navigation.ToPictureDetail) {
                navController.navigateToRepos(navigationEffect.picture)
            }
        }
    )
}
