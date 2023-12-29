package svg.assignment.presentation.navigation

import svg.assignment.core.Constants.GENERATE_IMAGE_SCREEN
import svg.assignment.core.Constants.HOME_SCREEN
import svg.assignment.core.Constants.RECENTS_SCREEN

sealed class Screens(val route: String) {
    object HomeScreen: Screens(HOME_SCREEN)
    object GenerateImageScreen: Screens(GENERATE_IMAGE_SCREEN)
    object RecentlyGenerateImageScreen: Screens(RECENTS_SCREEN)
}