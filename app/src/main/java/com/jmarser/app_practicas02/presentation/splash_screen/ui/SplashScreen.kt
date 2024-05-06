package com.jmarser.app_practicas02.presentation.splash_screen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Project: App_Practicas02
 * File: SplashScreen
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun SplashScreen(
    goToLogin: () -> Unit,
    goToMain: () -> Unit,
) {

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Pantalla de splash",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { goToLogin() }
        ) {
          Text(text = "Go To Login")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { goToMain() }
        ) {
            Text(text = "Go To Main")
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen({}, {})
}