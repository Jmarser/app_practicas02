package com.jmarser.app_practicas02.presentation.main_screen.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jmarser.app_practicas02.navigation.RoutesBottomNavBar

/**
 * Project: App_Practicas02
 * File: CustomBottomNavBar
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun CustomBottomNavBar(
    navController: NavController
) {

    val screens = listOf(
        RoutesBottomNavBar.userScreen,
        RoutesBottomNavBar.albunsScreen,
        RoutesBottomNavBar.portadasScreen
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination == screen.route,
                onClick = { navController.navigate(screen.route) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                },
                label = { Text(text = screen.title) },
                alwaysShowLabel = false
            )
        }
    }
}