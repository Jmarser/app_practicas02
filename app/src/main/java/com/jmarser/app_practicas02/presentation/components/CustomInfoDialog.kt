package com.jmarser.app_practicas02.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun CustomInfoDialog(
    textTitle: String? = null,
    textMessage: String,
    textBtnConfirm: String? = "Entendido",
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = { onConfirm() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                ) {
                    Image(imageVector = Icons.Filled.Info, contentDescription = null)
                }
                if (!textTitle.isNullOrEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = textTitle ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text = textMessage,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .align(Alignment.End),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { onConfirm() }
                    ) {
                        Text(
                            text = textBtnConfirm ?: "Aceptar",
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCustomInfoDialog() {
    CustomInfoDialog(
        textTitle = "TITULO",
        textMessage = "Mensaje del cuadro de diálogo",
        textBtnConfirm = "Entendido",
        {})
}