package com.jmarser.app_practicas02.presentation.login_screen.viewModel

import androidx.lifecycle.ViewModel
import com.jmarser.app_practicas02.domain.useCase.LoginUseCase
import com.jmarser.app_practicas02.presentation.login_screen.event.LoginEvent
import com.jmarser.app_practicas02.presentation.login_screen.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Project: App_Practicas02
 * File: LoginViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 04/05/2024
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase
): ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()


    fun onEvent(event: LoginEvent) {
        when(event){
            is LoginEvent.EmailChanged -> emailChanged(event.email)
            is LoginEvent.passwordChanged -> passwordChanged(event.password)
            is LoginEvent.EmailFocusChanged -> {
                if (!event.hasFocus){
                    validarEmail()
                }
            }
            is LoginEvent.passwordFocusChanged -> {
                if (!event.hasFocus){
                    validarPassword()
                }
            }
            is LoginEvent.LoginButtonPressed -> tryToLogin(event.email, event.password)
        }
    }

    private fun tryToLogin(email: String, password: String){

    }

    private fun validarPassword(){
        val currentState = _loginState.value as? LoginState.LoginDataState ?: return

        if (currentState is LoginState.LoginDataState){
            val isValid = useCase.validatePassword(currentState.password)
            _loginState.value = currentState.copy(
                isPasswordValid = isValid,
                passwordErrorMessage = if (isValid) null else "El password debe ser de al menos 6 caracteres.",
                isSubmitButtonEnabled = isValid && (currentState.isEmailValid ?: false)
            )
        }
    }

    private fun validarEmail(){
        val currentState = _loginState.value as? LoginState.LoginDataState ?: return

        if (currentState is LoginState.LoginDataState){
            val isValid = useCase.validateEmail(currentState.email)
            _loginState.value = currentState.copy(
                isEmailValid = isValid,
                emailErrorMessage = if (isValid) null else "Ingrese un email válido.",
                isSubmitButtonEnabled = isValid && (currentState.isPasswordValid ?: false)
            )
        }
    }

    private fun passwordChanged(password: String){
        val currentState = _loginState.value
        if (currentState is LoginState.LoginDataState){
            _loginState.value = currentState.copy(password = password)
        }else{
            _loginState.value = LoginState.LoginDataState(password = password)
        }
    }

    private fun emailChanged(email: String){
        val currentState = _loginState.value
        if (currentState is LoginState.LoginDataState){
            _loginState.value = currentState.copy(email = email)
        }else{
            _loginState.value = LoginState.LoginDataState(email = email)
        }
    }

    fun clearLoginForm(){
        val currentState = _loginState.value as? LoginState.LoginDataState
        currentState?.let {
            _loginState.value = it.copy(
                email = "",
                password = "",
                isEmailValid = null,
                isPasswordValid = null,
                emailErrorMessage = null,
                passwordErrorMessage = null,
                isSubmitButtonEnabled = false,
            )
        }
    }
}