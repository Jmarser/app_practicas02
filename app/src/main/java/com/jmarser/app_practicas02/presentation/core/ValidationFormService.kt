package com.jmarser.app_practicas02.presentation.core

/**
 * Project: App_Practicas02
 * File: AppPreferences
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024
 */

interface ValidationFormService {
    fun validarFiledNotEmpty(texto: String): Boolean
    fun validarPhone(telefono: String): Boolean
    fun validarEmail(email: String): Boolean
    fun passwordEquals(password: String, repeatPassword: String): Boolean
    fun passwordLengthMin(password: String): Boolean
    fun isValidPassword(password: String): Boolean
}