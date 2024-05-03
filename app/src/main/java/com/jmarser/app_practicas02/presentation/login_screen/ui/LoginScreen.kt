package com.jmarser.app_practicas02.presentation.login_screen.ui

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
 * File: LoginScreen
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun LoginScreen(
    goToMain: () -> Unit,
    goToRegister: () -> Unit,
    goToForgot: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Pantalla de login",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { goToMain() }
        ) {
            Text(text = "Go To Main")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { goToRegister() }
        ) {
            Text(text = "Go To Register")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { goToForgot() }
        ) {
            Text(text = "Go To Forgot")
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen({}, {}, {})
}