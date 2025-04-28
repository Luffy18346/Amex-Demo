package com.luffy18346.amexdemo.ui.navigation.routes

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.luffy18346.amexdemo.ui.feature.detail.DetailContract
import com.luffy18346.amexdemo.ui.feature.detail.DetailScreen
import com.luffy18346.amexdemo.ui.feature.detail.DetailViewModel
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.AUTHOR_NAME
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.FILE_NAME
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.IMAGE_HEIGHT
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.IMAGE_URL
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.IMAGE_WIDTH
import com.luffy18346.amexdemo.ui.navigation.Navigation.Routes.PICTURE_DETAILS
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.detailScreenRoute(navController: NavController) {
    composable(
        route = PICTURE_DETAILS, arguments = listOf(
        navArgument(AUTHOR_NAME) { type = NavType.StringType },
        navArgument(FILE_NAME) { type = NavType.StringType },
        navArgument(IMAGE_WIDTH) { type = NavType.LongType },
        navArgument(IMAGE_HEIGHT) { type = NavType.LongType },
        navArgument(IMAGE_URL) { type = NavType.StringType }
    )) {
        val authorName =
            requireNotNull(it.arguments?.getString(AUTHOR_NAME)) { "Author name is required as an argument" }
        val fileName =
            requireNotNull(it.arguments?.getString(FILE_NAME)) { "File name is required as an argument" }
        val imageWidth =
            requireNotNull(it.arguments?.getLong(IMAGE_WIDTH)) { "Image width is required as an argument" }
        val imageHeight =
            requireNotNull(it.arguments?.getLong(IMAGE_HEIGHT)) { "Image height is required as an argument" }
        val imageUrl =
            requireNotNull(it.arguments?.getString(IMAGE_URL)) { "Image url is required as an argument" }
        val viewModel: DetailViewModel = koinViewModel<DetailViewModel>()

        DetailScreenDestination(
            fileName,
            Uri.decode(imageUrl),
            authorName,
            imageWidth,
            imageHeight,
            viewModel,
            navController
        )
    }
}

@Composable
fun DetailScreenDestination(
    fileName: String, imageUrl: String, authorName: String,
    imageWidth: Long, imageHeight: Long,
    viewModel: DetailViewModel, navController: NavController
) {
    DetailScreen(
        fileName = fileName,
        imageUrl = imageUrl,
        authorName = authorName,
        imageWidth = imageWidth,
        imageHeight = imageHeight,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is DetailContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        },
    )
}
