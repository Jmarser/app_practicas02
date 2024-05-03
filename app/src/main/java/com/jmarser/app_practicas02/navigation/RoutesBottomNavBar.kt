package com.jmarser.app_practicas02.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DiscFull
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.ui.graphics.vector.ImageVector

sealed class RoutesBottomNavBar(
    val route: String,
    val title: String,
    val icon: ImageVector
){

    object userScreen: RoutesBottomNavBar(
        route = "USERS",
        title = "Users",
        icon = Icons.Filled.Person
    )

    object albunsScreen: RoutesBottomNavBar(
        route = "ALBUMES",
        title = "Albumes",
        icon = Icons.Filled.DiscFull
    )

    object portadasScreen: RoutesBottomNavBar(
        route = "PORTADAS",
        title = "Portadas",
        icon = Icons.Filled.PhotoLibrary
    )
}
