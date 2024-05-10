package com.jmarser.app_practicas02.presentation.register_screen.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jmarser.app_practicas02.R
import com.jmarser.app_practicas02.presentation.components.HeaderAuth
import com.jmarser.app_practicas02.presentation.components.PasswordInputField
import com.jmarser.app_practicas02.presentation.components.RoundedButtonWithPB
import com.jmarser.app_practicas02.presentation.components.RowArrowGoBack
import com.jmarser.app_practicas02.presentation.components.TextInputField
import com.jmarser.app_practicas02.presentation.register_screen.event.RegisterEvent
import com.jmarser.app_practicas02.presentation.register_screen.state.RegisterState
import com.jmarser.app_practicas02.presentation.register_screen.viewModel.RegisterViewModel

/**
 * Project: App_Practicas02
 * File: RegisterScreen
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun RegisterScreen(
    goToLogin: () -> Unit,
    goToMain: () -> Unit,
    goToForgot: () -> Unit,
    goToBack: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val registerState by viewModel.registerState.collectAsState()
    val registerDataState = registerState as? RegisterState.RegisterDataState

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            RowArrowGoBack(
                modifier = Modifier,
                null,
                {goToBack()}
            )

            HeaderAuth(
                modifier = Modifier,
                title = "REGISTRO"
            )

            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(all = 25.dp)
                    .background(
                        MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(25.dp)
                    ),
                contentAlignment = Alignment.Center,
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        4.dp,
                        Alignment.CenterVertically
                    )
                ){
                    TextInputField(
                        modifier = Modifier
                            .onFocusChanged {focusState ->
                                if(!focusState.isFocused){
                                    viewModel.onEvent(RegisterEvent.nameFocusChanged(hasFocus = false))
                                }
                            },
                        value = registerDataState?.name ?: "",
                        textLabel = "Nombre",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        onValueChange = { viewModel.onEvent(RegisterEvent.nameChanged(it)) },
                        errorMessage = registerDataState?.nameErrorMessage
                    )

                    TextInputField(
                        modifier = Modifier
                            .onFocusChanged { focusState ->
                                if(!focusState.isFocused){
                                    viewModel.onEvent(RegisterEvent.apellidosFocusChanged(hasFocus = false))
                                }
                            },
                        value = registerDataState?.apellidos ?: "",
                        textLabel = "Apellidos",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        onValueChange = { viewModel.onEvent(RegisterEvent.apellidosChanged(it))},
                        errorMessage = registerDataState?.apellidosErrorMessage
                    )

                    TextInputField(
                        modifier = Modifier
                            .onFocusChanged { focusState ->
                                if(!focusState.isFocused){
                                    viewModel.onEvent(RegisterEvent.phoneFocusChanged(hasFocus = false))
                                }
                            },
                        value = registerDataState?.phone ?: "",
                        textLabel = "Teléfono",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        onValueChange = {viewModel.onEvent(RegisterEvent.phoneChanged(it))},
                        errorMessage = registerDataState?.phoneErrorMessage
                    )

                    TextInputField(
                        modifier = Modifier
                            .onFocusChanged { focusState ->
                                if(!focusState.isFocused){
                                    viewModel.onEvent(RegisterEvent.emailFocusChanged(hasFocus = false))
                                }
                            },
                        value = registerDataState?.email ?: "",
                        textLabel = "Email",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        onValueChange = {viewModel.onEvent(RegisterEvent.emailChanged(it))},
                        errorMessage = registerDataState?.emailErrorMessage
                    )

                    PasswordInputField(
                        modifier = Modifier
                            .onFocusChanged { focusState ->
                                if(!focusState.isFocused){
                                    viewModel.onEvent(RegisterEvent.passwordFocusChanged(hasFocus = false))
                                }
                            },
                        value = registerDataState?.password ?: "",
                        textLabel = "Password",
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                        onValueChange = {viewModel.onEvent(RegisterEvent.passwordChanged(it))},
                        iconShow = R.drawable.ic_icon_show,
                        iconHide = R.drawable.ic_icon_hide,
                        errorMessage = registerDataState?.passwordErrorMessage
                    )

                    PasswordInputField(
                        modifier = Modifier
                            .onFocusChanged { focusState ->
                                if(!focusState.isFocused){
                                    viewModel.onEvent(RegisterEvent.repeatPasswordFocusChanged(hasFocus = false))
                                }
                            },
                        value = registerDataState?.repeatPassword ?: "",
                        textLabel = "Repeat Password",
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                        onValueChange = {viewModel.onEvent(RegisterEvent.repeatPasswordChanged(it))},
                        iconShow = R.drawable.ic_icon_show,
                        iconHide = R.drawable.ic_icon_hide,
                        errorMessage = registerDataState?.repeatPasswordErrorMessage
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    RoundedButtonWithPB(
                        text = "Registrarse",
                        onClick = {
                            if (registerDataState?.isSubmitButtonEnabled == true){

                            }else{
                                Toast.makeText(context, "Debe rellenar todos los campos del registro.", Toast.LENGTH_LONG).show()
                            }
                        },
                        isEnabled = registerDataState?.isSubmitButtonEnabled ?: false,
                        displayProgressBar = false
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp),
                        onClick = { goToForgot() }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(bottom = 24.dp),
                            text = "¿Has olvidado tu contraseña?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp),
                        onClick = { goToLogin() }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(bottom = 24.dp),
                            text = "¿Ya tienes cuenta?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }

                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen({}, {}, {}, {})
}