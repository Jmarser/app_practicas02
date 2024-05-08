package com.jmarser.app_practicas02.presentation.user_screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.app_practicas02.domain.useCase.GetAllUsersUseCase
import com.jmarser.app_practicas02.presentation.login_screen.state.LoginState
import com.jmarser.app_practicas02.presentation.user_screen.state.UsersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: App_Practicas02
 * File: UsersViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 07/05/2024
 */

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val useCase: GetAllUsersUseCase
): ViewModel() {


    private val _usersState = MutableStateFlow<UsersState>(UsersState.Idle)
    val usersState: StateFlow<UsersState> = _usersState.asStateFlow()

    init {
        getAllUsers()
    }

    private fun getAllUsers(){
        viewModelScope.launch {
            _usersState.value = UsersState.Loading

            val getUsersResult = useCase.getAllUsers()

            when(getUsersResult){
                is UsersState.Success -> {
                    if (!getUsersResult.data.isNullOrEmpty()){
                        _usersState.value = UsersState.Success(getUsersResult.data, "Tenemos usuarios.")
                    }else{
                        _usersState.value = UsersState.Failure("No hay usuario disponibles.")
                    }
                }
                is UsersState.Failure -> {
                    _usersState.value = UsersState.Failure("Error al obtener los datos.")
                }
                else -> {
                    _usersState.value = UsersState.Failure("Ha ocurrido un error desconocido.")
                }
            }
        }
    }
}