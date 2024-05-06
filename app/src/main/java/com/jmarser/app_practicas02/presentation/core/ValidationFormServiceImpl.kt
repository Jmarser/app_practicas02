package com.jmarser.app_practicas02.presentation.core

import androidx.core.text.isDigitsOnly

/**
 * Project: App_Practicas02
 * File: AppPreferences
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024
 */

class ValidationFormServiceImpl : ValidationFormService {

    override fun validarFiledNotEmpty(texto: String): Boolean {
        return texto.isNotEmpty() && texto.isNotBlank()
    }

    override fun validarPhone(telefono: String): Boolean {
        return telefono.length == 9 && telefono.isDigitsOnly()
    }

    override fun validarEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun passwordEquals(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }

    override fun passwordLengthMin(password: String): Boolean {
        return password.length >= 6
    }

    override fun isValidPassword(password: String): Boolean {
        val hasUppercase = password.any { it.isUpperCase() }
        val hasLowercase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasValidLength = password.length >= 8

        return hasUppercase && hasLowercase && hasDigit && hasValidLength
    }
}