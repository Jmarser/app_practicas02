package com.jmarser.app_practicas02.presentation.register_screen.viewModel

import androidx.lifecycle.ViewModel
import com.jmarser.app_practicas02.domain.useCase.RegisterUseCase
import com.jmarser.app_practicas02.presentation.register_screen.event.RegisterEvent
import com.jmarser.app_practicas02.presentation.register_screen.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Project: App_Practicas02
 * File: RegisterViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 09/05/2024
 */

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase
): ViewModel(){

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()


    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.nameChanged -> nameChanged(event.name)
            is RegisterEvent.nameFocusChanged -> {
                if (!event.hasFocus){
                    validarName()
                }
            }
            is RegisterEvent.apellidosChanged -> apellidosChanged(event.apellidos)
            is RegisterEvent.apellidosFocusChanged -> {
                if (!event.hasFocus){
                    validarApellidos()
                }
            }
            is RegisterEvent.emailChanged -> emailChanged(event.email)
            is RegisterEvent.emailFocusChanged -> {
                if (!event.hasFocus){
                    validarEmail()
                }
            }
            is RegisterEvent.phoneChanged -> phoneChanged(event.phone)
            is RegisterEvent.phoneFocusChanged -> {
                if (!event.hasFocus){
                    validarPhone()
                }
            }
            is RegisterEvent.passwordChanged -> passwordChanged(event.password)
            is RegisterEvent.passwordFocusChanged -> {
                if (!event.hasFocus){
                    validarPassword()
                }
            }
            is RegisterEvent.repeatPasswordChanged -> repeatPassword(event.repeatPassword)
            is RegisterEvent.repeatPasswordFocusChanged -> {
                if (!event.hasFocus){
                    validarRepeatPassword()
                }
            }
            is RegisterEvent.RegisterButtonPressed -> TODO()
        }
    }


    private fun nameChanged(name: String){
        val currentState = _registerState.value
        if(currentState is RegisterState.RegisterDataState){
            _registerState.value = currentState.copy(name = name)
        }else{
            _registerState.value = RegisterState.RegisterDataState(name = name)
        }
    }

    private fun apellidosChanged(apellidos: String){
        val currentState = _registerState.value
        if (currentState is RegisterState.RegisterDataState){
            _registerState.value = currentState.copy(apellidos = apellidos)
        }else{
            _registerState.value = RegisterState.RegisterDataState(apellidos = apellidos)
        }
    }

    private fun emailChanged(email: String){
        val currentState = _registerState.value
        if (currentState is RegisterState.RegisterDataState){
            _registerState.value = currentState.copy(email = email)
        }else{
            _registerState.value = RegisterState.RegisterDataState(email = email)
        }
    }

    private fun phoneChanged(phone: String){
        val currentState = _registerState.value
        if (currentState is RegisterState.RegisterDataState){
            _registerState.value = currentState.copy(phone = phone)
        }else{
            _registerState.value = RegisterState.RegisterDataState(phone = phone)
        }
    }
    private fun passwordChanged(password: String){
        val currentState = _registerState.value
        if (currentState is RegisterState.RegisterDataState){
            _registerState.value = currentState.copy(password = password)
        }else {
            _registerState.value = RegisterState.RegisterDataState(password = password)
        }
    }

    private fun repeatPassword(repeatPassword: String){
        val currentState = _registerState.value
        if (currentState is RegisterState.RegisterDataState){
            _registerState.value = currentState.copy(repeatPassword = repeatPassword)
        }else{
            _registerState.value = RegisterState.RegisterDataState(repeatPassword = repeatPassword)
        }
    }

    private fun validarName(){
        val currentState = _registerState.value as? RegisterState.RegisterDataState ?: return

        val isValid = useCase.validateFieldEmpty(currentState.name)
        val errorMessage = if (isValid) null else "El campo no puede estar vacío."

        updateRegisterState(isValid, errorMessage){ valid, message ->
            currentState.copy(
                isNameValid = valid,
                nameErrorMessage = message
            )
        }
    }

    private fun validarApellidos(){
        val currentState = _registerState.value as? RegisterState.RegisterDataState ?: return

        val isValid = useCase.validateFieldEmpty(currentState.apellidos)
        val errorMessage = if (isValid) null else "El campo no puede estar vacío."

        updateRegisterState(isValid, errorMessage){ valid, message ->
            currentState.copy(
                isApellidosValid = valid,
                apellidosErrorMessage = message
            )
        }
    }

    private fun validarEmail(){
        val currentState = _registerState.value as? RegisterState.RegisterDataState ?: return

        val isValid = useCase.validateEmail(currentState.email)
        val errorMessage = if (isValid) null else "Indique un email válido."

        updateRegisterState(isValid, errorMessage){valid, messsage ->
            currentState.copy(
                isEmailValid = valid,
                emailErrorMessage = messsage
            )
        }
    }

    private fun validarPhone(){
        val currentState = _registerState.value as? RegisterState.RegisterDataState ?: return

        val isValid = useCase.validatePhone(currentState.phone)
        val errorMessage = if (isValid) null else "Debe indicar un teléfono válido."

        updateRegisterState(isValid, errorMessage){valid, message ->
            currentState.copy(
                isPhoneValid = valid,
                phoneErrorMessage = message
            )
        }
    }

    private fun validarPassword(){
        val currentState = _registerState.value as? RegisterState.RegisterDataState ?: return

        val isValid = useCase.validatePassword(currentState.password)
        val errorMessage = if (isValid) null else "Longitud mínima de 6 caracteres."

        updateRegisterState(isValid, errorMessage){valid, message ->
            currentState.copy(
                isPasswordValid = valid,
                passwordErrorMessage = message
            )
        }
    }

    private fun validarRepeatPassword(){
        val currentState = _registerState.value as? RegisterState.RegisterDataState ?: return

        val isValid = useCase.validateEqualsPassword(currentState.password, currentState.repeatPassword)
        val errorMessage = if (isValid) null else "Las contraseñas no coinciden."

        updateRegisterState(isValid, errorMessage){valid, message ->
            currentState.copy(
                isRepeatPasswordValid = valid,
                repeatPasswordErrorMessage = message
            )
        }
    }

    private fun updateRegisterState(isFieldValid: Boolean, fieldErrorMessage: String?, updateField: (Boolean, String?) -> RegisterState.RegisterDataState){
        val currentState = _registerState.value as? RegisterState.RegisterDataState ?: return
        _registerState.value = updateField(isFieldValid, fieldErrorMessage).copy(
            isSubmitButtonEnabled = areAllFieldValid(currentState)
        )
    }

    private fun areAllFieldValid(currentState: RegisterState.RegisterDataState): Boolean{
        return listOfNotNull(
            currentState.isNameValid,
            currentState.isApellidosValid,
            currentState.isPhoneValid,
            currentState.isEmailValid,
            currentState.isPasswordValid,
            currentState.isRepeatPasswordValid
        ).all { it }
    }
}