package com.jmarser.app_practicas02.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.app_practicas02.R

/**
 * Project: App_Practicas02
 * File: HeaderAuth
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/05/2024
 */

@Composable
fun HeaderAuth(
    modifier: Modifier = Modifier,
    title: String? = null
) {
    Column (
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(24.dp))
        Image(
            modifier = Modifier
                .size(150.dp),
            painter = painterResource(id = R.drawable.logo_sin),
            contentDescription = null
        )

        if (!title.isNullOrEmpty()){
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewHeaderAuth(modifier: Modifier = Modifier) {
    HeaderAuth(modifier,"Texto de la cabecera")
}