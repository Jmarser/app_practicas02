package com.jmarser.app_practicas02.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jmarser.app_practicas02.presentation.albuns_screen.ui.AlbunsScreen
import com.jmarser.app_practicas02.presentation.portadas_screen.ui.PortadasScreen
import com.jmarser.app_practicas02.presentation.user_screen.ui.UserScreen

/**
 * Project: App_Practicas02
 * File: MainNavBarGraph
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun MainNavBarGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = RoutesBottomNavBar.userScreen.route,
        route = RoutesNavigation.MainScreen.route
    ){
        composable(
            route = RoutesBottomNavBar.userScreen.route
        ){
            UserScreen()
        }
        composable(
            route = RoutesBottomNavBar.albunsScreen.route
        ){
            AlbunsScreen()
        }
        composable(
            route = RoutesBottomNavBar.portadasScreen.route
        ){
            PortadasScreen()
        }
    }
}