package com.jmarser.app_practicas02.navigation

import androidx.navigation.NamedNavArgument

sealed class RoutesNavigation(
    val route: String,
    val arguments: List<NamedNavArgument>
) {

    // Rutas de navegación de las pantallas de autenticacion
    object AuthScreens : RoutesNavigation("authScreens", emptyList()) // Ruta principal del grafo de autenticacion
    object SpashScreen: RoutesNavigation("spash", emptyList())
    object LoginScreen: RoutesNavigation("login", emptyList())
    object RegisterScreen: RoutesNavigation("register", emptyList())
    object ForgotScreen: RoutesNavigation("forgot", emptyList())

    object MainScreen: RoutesNavigation("main", emptyList()) // Ruta principal para la navegación inferior
}
