package com.joel.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joel.profile.utilities.ProfileItems

@Composable
fun ProfileScreen(
    onOptionClick: (ProfileItems) -> Unit
){

    ProfileMenu(
        onOptionClick = { item ->
            onOptionClick(item)
        }
    )
}


@Composable
fun ProfileMenu(
    onOptionClick: (ProfileItems) -> Unit
) {


    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ){
        item {
            UserImageAndName()
        }
        items(profileItems){items ->
            OptionsItemStyle(
                item = items,
                onOptionClick = { item ->
                    onOptionClick(item)
                }
            )
        }
    }
}

@Composable
fun UserImageAndName() {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(50),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 0.dp
                ),
                modifier = Modifier
                    .size(150.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.round_person_24),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = "Joel",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(vertical = 6.dp)
            )
        }
    }
}

@Composable
fun OptionsItemStyle(
    item: ProfileItems,
    onOptionClick : (ProfileItems) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onOptionClick(item)
            }
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Icon(
            modifier = Modifier
                .size(32.dp),
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            tint = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(weight = 3f, fill = false)
                    .padding(start = 16.dp)
            ) {

                Text(
                    text = item.title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Cursive
                    )
                )

                Spacer(modifier = Modifier.height(2.dp))


            }

            Icon(
                modifier = Modifier
                    .weight(weight = 1f, fill = false),
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = item.title,
                tint = Color(0xFFFE6833)
            )
        }
    }
}




val profileItems = listOf(
    ProfileItems(
            icon = R.drawable.round_person_24,
            title = "User Preference",
        "/dietPreference"
        ),
    ProfileItems(
            icon = R.drawable.round_nutrition_tracking_24,
            title = "Nutrition Tracking",
        "/nutritionTRacking"
        ),
    ProfileItems(
            icon = R.drawable.round_favorite_24,
            title = "Favourites",
        "/favourites"
        ),
    ProfileItems(
            icon = R.drawable.round_settings_24,
            title = "Settings",
        "/settings"
        ),
    )

