package com.jmarser.app_practicas02.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Project: App_Practicas02
 * File: RoundedButtonWithPB
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 04/05/2024
 */

@Composable
fun RoundedButtonWithPB(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String,
    heightButton: Int = 50,
    widthBorderStroke: Int = 0,
    percentRounded: Int = 50,
    borderColor: Color = Color.Transparent,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = Color.White,
    contentColor: Color = Color.White,
    disableBackgroundColor: Color = Color.Gray,
    disableContentColor: Color = Color.Gray,
    disabledTextColor: Color = Color.White,
    displayProgressBar: Boolean = false,
    progressBarColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    if (!displayProgressBar){
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .height(heightButton.dp)
                .border(
                    BorderStroke(
                        width = widthBorderStroke.dp,
                        color = borderColor
                    ),
                    shape = RoundedCornerShape(percentRounded.dp)
                ),
            onClick = onClick,
            shape = RoundedCornerShape(percentRounded.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isEnabled) backgroundColor else disableBackgroundColor,
                contentColor = if (isEnabled) contentColor else disableContentColor,
            )
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    color = if (isEnabled) textColor else disabledTextColor
                )
            )
        }
    }else{
        CircularProgressIndicator(
            modifier = modifier.size(heightButton.dp),
            color = progressBarColor,
            strokeWidth = 6.dp
        )
    }
}



@Preview(showSystemUi = false)
@Composable
fun PreviewRoundedButtonWithPB() {
    RoundedButtonWithPB(
        text = "púlsame",
        isEnabled = true,
        displayProgressBar = false,
        onClick = {}
    )
}
