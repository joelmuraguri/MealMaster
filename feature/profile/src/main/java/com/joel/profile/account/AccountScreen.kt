package com.joel.profile.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

// should use room
// card that holds diets, allergies, nutrients, on click launches a bottom sheet
// holds image and user name
// should have a dialog for image and name input
@Composable
fun AccountsScreen(){

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "USER DETAILS",
            fontSize = 25.sp
        )
    }
}



