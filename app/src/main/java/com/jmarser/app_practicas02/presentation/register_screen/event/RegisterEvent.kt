package com.jmarser.app_practicas02.presentation.register_screen.event

sealed class RegisterEvent{
    data class nameChanged(val name: String): RegisterEvent()
    data class apellidosChanged(val apellidos: String): RegisterEvent()
    data class emailChanged(val email: String): RegisterEvent()
    data class phoneChanged(val phone: String): RegisterEvent()
    data class passwordChanged(val password: String): RegisterEvent()
    data class repeatPasswordChanged(val repeatPassword: String): RegisterEvent()
    data class nameFocusChanged(val hasFocus: Boolean): RegisterEvent()
    data class apellidosFocusChanged(val hasFocus: Boolean): RegisterEvent()
    data class phoneFocusChanged(val hasFocus: Boolean): RegisterEvent()
    data class emailFocusChanged(val hasFocus: Boolean): RegisterEvent()
    data class passwordFocusChanged(val hasFocus: Boolean): RegisterEvent()
    data class repeatPasswordFocusChanged(val hasFocus: Boolean): RegisterEvent()
    data class RegisterButtonPressed(
        val name: String,
        val apellidos: String,
        val phone: String,
        val email: String,
        val password: String,
    ): RegisterEvent()

}
