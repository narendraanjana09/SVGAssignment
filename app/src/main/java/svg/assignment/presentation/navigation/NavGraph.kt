package svg.assignment.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import svg.assignment.presentation.generateImage.GenerateImageScreen
import svg.assignment.presentation.home.HomeScreen
import svg.assignment.presentation.recentlyGeneratedImages.RecentlyGenerateImageScreen

@Composable
@ExperimentalAnimationApi
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route,
    ) {
        composable(
            route = Screens.HomeScreen.route,
        ) {
            HomeScreen(
                navigateToGenerateImageScreen = {
                    navController.navigate(Screens.GenerateImageScreen.route)
                },
                navigateToRecentsScreen = {
                    navController.navigate(Screens.RecentlyGenerateImageScreen.route)
                }
            )
        }
        composable(
            route = Screens.GenerateImageScreen.route,
        ) {
            GenerateImageScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = Screens.RecentlyGenerateImageScreen.route,
        ) {
            RecentlyGenerateImageScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}