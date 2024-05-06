package com.jmarser.app_practicas02.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Project: App_Practicas02
 * File: TextInputField
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    value: String,
    textLabel: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
    showTrailingIcon: Boolean = false,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: () -> Unit = {}
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = if ( !errorMessage.isNullOrEmpty()) 10.dp else 20.dp)
    ){
       TextField(
           modifier = modifier
               .fillMaxWidth(),
           value = value,
           onValueChange = onValueChange,
           label = {
               Text(text = textLabel)
           },
           shape = RoundedCornerShape(25),
           keyboardOptions = KeyboardOptions.Default.copy(
               keyboardType = keyboardType,
               imeAction = imeAction
           ),
           singleLine = true,
           maxLines = 1,
           trailingIcon = if (showTrailingIcon){
               {
                   IconButton(
                       onClick = { onTrailingIconClick() }
                   ) {
                       if (trailingIcon != null) {
                           Icon(
                               imageVector = trailingIcon,
                               contentDescription = null
                           )
                       }
                   }
               }
           }else null
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
fun PreviewTextInputField() {
    TextInputField(
        modifier = Modifier,
        "Texto",
        "Texto de ayuda",
        KeyboardType.Email,
        ImeAction.Next,
        {},
        "Mensaje error",
        true,
        Icons.Default.Info,
    )
}