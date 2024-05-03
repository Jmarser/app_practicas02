package com.jmarser.app_practicas02.presentation.main_screen.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jmarser.app_practicas02.navigation.MainNavBarGraph

/**
 * Project: App_Practicas02
 * File: MainScreen
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun MainScreen(
    navController: NavHostController,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {},
        bottomBar = { CustomBottomNavBar(navController = navController)}
    ) {
        var modifier = Modifier.padding(it)
        MainNavBarGraph(navController = navController)
    }
}