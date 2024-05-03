package com.jmarser.app_practicas02.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jmarser.app_practicas02.presentation.main_screen.ui.MainScreen

/**
 * Project: App_Practicas02
 * File: RootNavGraph
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun RootNavGraph() {

    val navController = rememberNavController()
    val navBarController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = RoutesNavigation.AuthScreens.route
        ){
            AuthNavGraph(navController)

        composable(
            route = RoutesNavigation.MainScreen.route,
            arguments = emptyList()
        ){
            MainScreen(navController = navBarController)
        }
    }
}