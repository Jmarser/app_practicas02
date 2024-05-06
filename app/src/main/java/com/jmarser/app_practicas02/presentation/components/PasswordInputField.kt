package com.jmarser.app_practicas02.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmarser.app_practicas02.R

/**
 * Project: App_Practicas02
 * File: PasswordInputField
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    value: String,
    textLabel: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit,
    iconShow: Int,
    iconHide: Int,
    errorMessage: String? = null
) {

    var passVisible by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = if (!errorMessage.isNullOrEmpty()) 10.dp else 20.dp)
    ){
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        keyboardController?.hide() // Oculta el teclado cuando el TextField pierde el foco
                    }
                },
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(text = textLabel)
            },
            trailingIcon = {
                IconToggleButton(
                    checked = passVisible,
                    onCheckedChange = { passVisible = it }
                ) {
                    Icon(
                        modifier = modifier
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = if (passVisible) iconShow else iconHide),
                        contentDescription = null,
                    )
                }
            },
            visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(25),
            keyboardActions = KeyboardActions(
                onGo = {
                    keyboardController?.hide()
                }
            ),
            singleLine = true,
            maxLines = 1
        )

        if (!errorMessage.isNullOrEmpty()){
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                text = errorMessage,
                fontSize = 12.sp,
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }
    }
}


@Preview(showSystemUi = false)
@Composable
fun PreviewPasswordInputField() {
    PasswordInputField(
        modifier = Modifier,
        "Texto",
        "Texto ayuda",
        KeyboardType.Password,
        ImeAction.Next,
        {},
        R.drawable.ic_icon_show,
        R.drawable.ic_icon_hide,
        "Mensaje error"
    )
}