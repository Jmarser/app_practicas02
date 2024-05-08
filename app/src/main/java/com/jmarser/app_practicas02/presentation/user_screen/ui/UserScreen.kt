package com.jmarser.app_practicas02.presentation.user_screen.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jmarser.app_practicas02.presentation.components.EmptyDataMessageScreen
import com.jmarser.app_practicas02.presentation.components.LoadingScreen
import com.jmarser.app_practicas02.presentation.user_screen.state.UsersState
import com.jmarser.app_practicas02.presentation.user_screen.viewModel.UsersViewModel

/**
 * Project: App_Practicas02
 * File: UserScreen
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun UserScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {

    val context = LocalContext.current
    val usersState by viewModel.usersState.collectAsState()

    LaunchedEffect(usersState){
        when(usersState){
            is UsersState.Success -> {
                Toast.makeText(context, (usersState as UsersState.Success).message, Toast.LENGTH_LONG).show()
            }
            is UsersState.Failure -> {
                Toast.makeText(context, (usersState as UsersState.Failure).message, Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ){

            if (usersState is UsersState.Loading){
                LoadingScreen()
                Log.d("ALL_USERS", "Loading")
            }

            if (usersState is UsersState.Failure && (usersState as UsersState.Failure).message.isNotEmpty()){
                EmptyDataMessageScreen(textMessage = (usersState as UsersState.Failure).message)
                Log.d("ALL_USERS", "Error o sin datos")
            }else if (usersState is UsersState.Success && (usersState as UsersState.Success).data.isNotEmpty()){
                Log.d("ALL_USERS", "Con datos. ${(usersState as UsersState.Success).data}")
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ){
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(all = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        items(
                            (usersState as UsersState.Success).data,
                            key = {
                                it.id!!
                            }
                        ){
                            ItemRowUser(user = it)
                        }
                    }
                }
            }
        }
    }
}


/*
@Preview(showSystemUi = true)
@Composable
fun PreviewUserScreen() {
    UserScreen()
}*/
