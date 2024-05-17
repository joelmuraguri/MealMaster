package com.joel.mealmaster.utils.connectivity

sealed class ConnectivityObserverEvent {
    data object OnDismissNetworkError : ConnectivityObserverEvent()
}
