package com.joel.discover

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joel.domain.model.Recipe
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun DiscoverScreen(
    discoverViewModel: DiscoverViewModel
){

    val state = discoverViewModel.state.value

    if (state.randomRecipe.isNotEmpty()){
        Box {
            RecipeItem(
                modifier = Modifier
                    .fillMaxWidth(),
                recipe = state.randomRecipe.first(),
                onClick = {

                }
            )
        }
    }
    if (state.isLoading){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
    if (state.error.isNotEmpty()){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(text = state.error)
            Button(onClick = { discoverViewModel.fetchRandomRecipe() }) {
                Text(text = "Retry")
            }
        }
    }

//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        Text(
//            text = "DISCOVER",
//            fontSize = 25.sp
//        )
//    }
}



@Composable
fun RecipeItem(
    modifier: Modifier,
    recipe: Recipe,
    onClick : (Recipe) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(all = 4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick(recipe)
            },
        horizontalAlignment = Alignment.Start
    ) {
        CoilImage(
            imageModel = recipe.image,
            shimmerParams = ShimmerParams(
                baseColor = Color(0xFF180E36),
                highlightColor = Color(0XFF423460),
                durationMillis = 500,
                dropOff = 0.65F,
                tilt = 20F
            ),
            failure = {

            },
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 1000),
            modifier = modifier.clip(RoundedCornerShape(8.dp)),
            contentDescription = "Movie item"
        )

    }
}
