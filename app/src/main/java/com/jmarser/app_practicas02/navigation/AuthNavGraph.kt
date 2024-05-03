package com.jmarser.app_practicas02.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jmarser.app_practicas02.presentation.forgot_screen.ui.ForgotScreen
import com.jmarser.app_practicas02.presentation.login_screen.ui.LoginScreen
import com.jmarser.app_practicas02.presentation.register_screen.ui.RegisterScreen
import com.jmarser.app_practicas02.presentation.splash_screen.ui.SplashScreen

/**
 * Project: App_Practicas02
 * File: AuthNavGraph
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

fun NavGraphBuilder.AuthNavGraph(navcontroller: NavController) {

    navigation(
        startDestination = RoutesNavigation.SpashScreen.route,
        route = RoutesNavigation.AuthScreens.route
    ) {
        composable(
            route = RoutesNavigation.SpashScreen.route
        ) {
            SplashScreen(
                goToLogin = {
                    navcontroller.navigate(RoutesNavigation.LoginScreen.route) {
                        //Eliminamos todas las entradas en la pila de navegación incluyendo la especificada para no poder regresar con el botón back
                        popUpTo(RoutesNavigation.SpashScreen.route) {
                            inclusive = true
                        }
                    }
                },
                goToMain = {
                    navcontroller.goToMainScreen()
                }
            )
        }
        composable(
            route = RoutesNavigation.LoginScreen.route
        ) {
            LoginScreen(
                goToMain = {
                    navcontroller.goToMainScreen()
                },
                goToRegister = {
                    navcontroller.navigate(RoutesNavigation.RegisterScreen.route)
                },
                goToForgot = {
                    navcontroller.navigate(RoutesNavigation.ForgotScreen.route)
                }
            )
        }
        composable(
            route = RoutesNavigation.RegisterScreen.route
        ) {
            RegisterScreen(
                goToLogin = {
                    navcontroller.navigate(RoutesNavigation.LoginScreen.route)
                },
                goToMain = {
                    navcontroller.goToMainScreen()
                },
                goToForgot = {
                    navcontroller.navigate(RoutesNavigation.ForgotScreen.route)
                },
                goToBack = {
                    navcontroller.popBackStack()
                }
            )
        }
        composable(
            route = RoutesNavigation.ForgotScreen.route
        ) {
            ForgotScreen(
                goToLogin = {
                  navcontroller.navigate(RoutesNavigation.LoginScreen.route)
                },
                goToRegister = {
                    navcontroller.navigate(RoutesNavigation.RegisterScreen.route)
                },
                goToBack = {
                    navcontroller.popBackStack()
                }
            )
        }
    }
}