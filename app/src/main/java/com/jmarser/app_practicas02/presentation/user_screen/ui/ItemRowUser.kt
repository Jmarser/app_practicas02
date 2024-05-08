package com.jmarser.app_practicas02.presentation.user_screen.ui


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.app_practicas02.domain.modelsDM.UserDm

@Composable
fun ItemRowUser(
    modifier: Modifier = Modifier,
    user: UserDm,
    idUserSelected: MutableState<Int>? = null
) {
    Card (
        modifier = modifier
            .padding(3.dp)
            .wrapContentWidth()
            .background(Color.Transparent)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 0.dp
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            1.dp,
            Color.DarkGray
        )
    ){
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Row (
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Nombre:",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 5.dp),
                    text = user.firstname ?: "Sin nombre"
                )
            }
            Row (
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Email:",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 5.dp),
                    text = user.email ?: "Sin email"
                )
            }
        }
    }
}

/*@Preview(showSystemUi = true)
@Composable
fun ItemRowUserPreview() {
    ItemRowUser(modifier = Modifier)
}*/
