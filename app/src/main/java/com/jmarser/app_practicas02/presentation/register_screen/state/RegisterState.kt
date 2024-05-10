package com.jmarser.app_practicas02.presentation.register_screen.state

sealed class RegisterState{

    object Idle: RegisterState()
    object Loading: RegisterState()
    data class Success(
        val messageSuccess: String) : RegisterState()
    data class Failure(val messageFailure: String) : RegisterState()

    data class RegisterDataState(
        val name: String = "",
        val nameErrorMessage: String? = null,
        val isNameValid: Boolean? = false,
        val apellidos: String = "",
        val apellidosErrorMessage: String? = null,
        val isApellidosValid: Boolean? = false,
        val phone: String = "",
        val phoneErrorMessage: String? = null,
        val isPhoneValid: Boolean? = false,
        val email: String = "",
        val emailErrorMessage: String? = null,
        val isEmailValid: Boolean? = false,
        val password: String = "",
        val passwordErrorMessage: String? = null,
        val isPasswordValid: Boolean? = false,
        val repeatPassword: String = "",
        val repeatPasswordErrorMessage: String? = null,
        val isRepeatPasswordValid: Boolean? = false,
        val isSubmitButtonEnabled: Boolean = false,
    ): RegisterState()
}
