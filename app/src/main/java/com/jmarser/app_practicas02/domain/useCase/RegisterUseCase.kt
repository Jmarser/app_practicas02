package com.jmarser.app_practicas02.domain.useCase

import com.jmarser.app_practicas02.presentation.core.ValidationFormService
import javax.inject.Inject

/**
 * Project: App_Practicas02
 * File: registerUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 09/05/2024
 */

class RegisterUseCase @Inject constructor(
    private val validateForm: ValidationFormService
) {

    fun validateFieldEmpty(texto: String): Boolean{
        return validateForm.validarFiledNotEmpty(texto)
    }

    fun validateEmail(email: String): Boolean{
        return validateForm.validarEmail(email)
    }

    fun validatePhone(phone: String): Boolean{
        return validateForm.validarPhone(phone)
    }

    fun validatePassword(password: String): Boolean{
        return validateForm.passwordLengthMin(password)
    }

    fun validateEqualsPassword(password: String, repeatPassword: String): Boolean{
        return validateForm.passwordEquals(password, repeatPassword)
    }
}