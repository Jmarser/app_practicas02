package com.jmarser.app_practicas02.navigation

import androidx.navigation.NavController

/**
 * Project: App_Practicas02
 * File: NavControllerExt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

fun NavController.goToMainScreen() {
    popBackStack(
        RoutesNavigation.AuthScreens.route,
        inclusive = false
    )
    navigate(RoutesNavigation.MainScreen.route)
}
