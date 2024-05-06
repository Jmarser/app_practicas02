package com.jmarser.app_practicas02.domain.useCase

import com.jmarser.app_practicas02.presentation.core.ValidationFormService
import javax.inject.Inject

/**
 * Project: App_Practicas02
 * File: LoginUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2024
 */

class LoginUseCase @Inject constructor(
    private val validationForm: ValidationFormService
) {

    fun validateEmail(email: String): Boolean {
        return validationForm.validarEmail(email)
    }

    fun validatePassword(password: String): Boolean {
        return validationForm.passwordLengthMin(password)
    }
}