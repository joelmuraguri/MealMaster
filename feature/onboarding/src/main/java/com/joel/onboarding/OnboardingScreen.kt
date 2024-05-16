package com.joel.onboarding

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.joel.onboarding.utils.OnboardingItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    val items = OnboardingItems.getData()
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState(
        pageCount = { items.size }
    )

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pageState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            OnBoardingItem(
                items = items[page],
                size = items.size,
                index = pageState.currentPage,
                onButtonClick = {
                    if (pageState.currentPage + 1 < items.size) scope.launch {
                        pageState.scrollToPage(pageState.currentPage + 1)
                    }
                },
                onSkipClick = {}
            )
        }
    }
}


@Composable
fun BottomSection(size: Int, index: Int, onButtonClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Indicators(size, index)

        FloatingActionButton(
            onClick = { onButtonClick() },
            modifier = Modifier
                .height(40.dp)
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(horizontal = 19.dp)
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                )
                Icon(
                    Icons.Outlined.KeyboardArrowRight,
                    tint = Color.White,
                    contentDescription = "Localized description"
                )
            }
        }
    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy), label = ""
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0XFFF8E2E7)
            )
    ) {

    }
}

@Composable
fun OnBoardingItem(
    items: OnboardingItems,
    size: Int,
    index: Int,
    onButtonClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = items.image).apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                }).build()
            ),
            contentDescription = stringResource(id = items.titleHeader),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextButton(
                onClick = onSkipClick,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterEnd),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Skip",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,

                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.8f))
                .align(Alignment.BottomCenter)
                .padding(vertical = 25.dp, horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = items.titleHeader),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(id = items.screenDescription),
                style = MaterialTheme.typography.labelLarge,
                color = Color.White
            )
            BottomSection(
                size = size , index = index,
                onButtonClick = onButtonClick
            )
        }
    }
}

