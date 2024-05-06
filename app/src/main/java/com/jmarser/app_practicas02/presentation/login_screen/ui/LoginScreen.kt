package com.jmarser.app_practicas02.presentation.login_screen.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jmarser.app_practicas02.R
import com.jmarser.app_practicas02.presentation.components.HeaderAuth
import com.jmarser.app_practicas02.presentation.components.PasswordInputField
import com.jmarser.app_practicas02.presentation.components.RoundedButtonWithPB
import com.jmarser.app_practicas02.presentation.components.TextInputField
import com.jmarser.app_practicas02.presentation.login_screen.event.LoginEvent
import com.jmarser.app_practicas02.presentation.login_screen.state.LoginState
import com.jmarser.app_practicas02.presentation.login_screen.viewModel.LoginViewModel

/**
 * Project: App_Practicas02
 * File: LoginScreen
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun LoginScreen(
    goToMain: () -> Unit,
    goToRegister: () -> Unit,
    goToForgot: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val loginState by viewModel.loginState.collectAsState()
    val loginDataState = loginState as? LoginState.LoginDataState

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HeaderAuth(
                modifier = Modifier,
                title = "Login"
            )
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(all = 25.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(25.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        4.dp,
                        Alignment.CenterVertically
                    )
                ) {
                    TextInputField(
                        modifier = Modifier
                            .onFocusChanged { focusState ->
                                if (!focusState.isFocused) {
                                    viewModel.onEvent(LoginEvent.EmailFocusChanged(hasFocus = false))
                                }
                            },
                        value =  loginDataState?.email ?: "",
                        textLabel = "Usuario",
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next,
                        onValueChange = {
                            viewModel.onEvent(LoginEvent.EmailChanged(it))
                            viewModel.onEvent(LoginEvent.EmailFocusChanged(hasFocus = false))
                        },
                        errorMessage = loginDataState?.emailErrorMessage
                    )

                    PasswordInputField(
                        modifier = Modifier
                            .onFocusChanged { focusState ->
                                if (!focusState.isFocused) {
                                    viewModel.onEvent(LoginEvent.passwordFocusChanged(hasFocus = false))
                                }
                            },
                        value = loginDataState?.password ?: "",
                        textLabel = "Password",
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                        onValueChange = {
                            viewModel.onEvent(LoginEvent.passwordChanged(it))
                            viewModel.onEvent(LoginEvent.passwordFocusChanged(hasFocus = false))
                        },
                        iconShow = R.drawable.ic_icon_show,
                        iconHide = R.drawable.ic_icon_hide,
                        errorMessage = loginDataState?.passwordErrorMessage
                    )

                    Spacer(Modifier.height(16.dp))

                    RoundedButtonWithPB(
                        modifier = Modifier
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) {
                                    viewModel.onEvent(LoginEvent.passwordFocusChanged(hasFocus = false))
                                }
                            },
                        text = "Iniciar sesión",
                        onClick = {
                            if (loginDataState?.isSubmitButtonEnabled == true) {
                                /*viewModel.onEvent(
                                    LoginEvent.LoginButtonPressed(
                                        loginDataState?.email ?: "",
                                        loginDataState?.password ?: ""
                                    )
                                )*/
                                goToMain()
                            }else{
                                Toast.makeText(context, "Ingrese datos de identificación", Toast.LENGTH_SHORT).show()
                            }
                        },
                        isEnabled = loginDataState?.isSubmitButtonEnabled ?: false,
                        displayProgressBar = loginState is LoginState.Loading
                    )

                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp),
                        onClick = { goToForgot() }
                    ) {
                        Text(
                            text = "¿Has olvidado tu contraseña?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp),
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(
                            2.dp,
                            MaterialTheme.colorScheme.primary
                        ),
                        onClick = { goToRegister() }
                    ) {
                        Text(
                            text = "¿No tienes cuenta?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }

                    Text(
                        text = "Crear nueva cuenta de usuario",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen({}, {}, {})
}