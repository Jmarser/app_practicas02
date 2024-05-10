package com.jmarser.app_practicas02.presentation.login_screen.event

sealed class LoginEvent{
    data class EmailChanged(val email: String): LoginEvent()
    data class passwordChanged(val password: String): LoginEvent()
    data class EmailFocusChanged(val hasFocus: Boolean): LoginEvent()
    data class passwordFocusChanged(val hasFocus: Boolean): LoginEvent()
    data class LoginButtonPressed(val email: String, val password: String): LoginEvent()
}
