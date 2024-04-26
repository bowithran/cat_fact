package jp.speakbuddy.edisonandroidexercise.feature.fact.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import jp.speakbuddy.edisonandroidexercise.feature.fact.FactListScreen
import jp.speakbuddy.edisonandroidexercise.feature.fact.FactScreen

@Composable
fun FactNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.FactMain.route
    ) {
        composable(
            route = Screen.FactMain.route
        ) {
            FactScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.FactList.route,
        ) {
            FactListScreen(navController = navController)
        }
    }
}