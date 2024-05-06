package com.jmarser.app_practicas02.presentation.login_screen.state

sealed class LoginState{

    object Idle: LoginState()
    object Loading: LoginState()
    data class Success(
        val message: String
    ): LoginState()
    data class Failure( val message: String): LoginState()

    data class LoginDataState(
        val email: String = "",
        val password: String = "",
        val isEmailValid: Boolean? = null,
        val isPasswordValid: Boolean? = null,
        val emailErrorMessage: String? = null,
        val passwordErrorMessage: String? = null,
        val isSubmitButtonEnabled: Boolean = false,
        val isCheckButton: Boolean = false
    ): LoginState()
}
