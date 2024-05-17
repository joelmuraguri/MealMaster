package com.joel.mealmaster.utils.connectivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joel.data.models.ConnectivityStatus
import com.joel.mealmaster.di.ViewModelFactory
import com.joel.mealmaster.ui.theme.MealMasterTheme

@Composable
fun ConnectivityObserverLayout(
    viewModel: ConnectivityObserverViewModel = viewModel(factory = ViewModelFactory.Factory),
    content: @Composable () -> Unit
) {
    ConnectivityObserverLayoutContent(
        status = viewModel.state.status,
        onDismissNetwork = {
            viewModel.onEvent(ConnectivityObserverEvent.OnDismissNetworkError)
        },
        content = content
    )
}

@Composable
fun ConnectivityObserverLayoutContent(
    status: ConnectivityStatus,
    onDismissNetwork: () -> Unit,
    content: @Composable () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        content()
        when (status) {
            ConnectivityStatus.LOSING,
            ConnectivityStatus.LOST,
            ConnectivityStatus.UNAVAILABLE -> {

            }
            else -> Unit
        }
    }
}

@Preview
@Composable
fun ConnectivityObserverLayoutPreview() {
    MealMasterTheme {
        ConnectivityObserverLayoutContent(
            status = ConnectivityStatus.AVAILABLE,
            onDismissNetwork = { },
            content = { }
        )
    }
}