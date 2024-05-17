package com.joel.mealmaster.utils.connectivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.data.models.ConnectivityStatus
import com.joel.data.repo.ConnectivityObserver
import kotlinx.coroutines.launch

class ConnectivityObserverViewModel (
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    var state by mutableStateOf(ConnectivityState())
        private set

    init {
        getNetworkStatus()
    }

    fun onEvent(event: ConnectivityObserverEvent) {
        when (event) {
            ConnectivityObserverEvent.OnDismissNetworkError -> {
                state = state.copy(
                    status = ConnectivityStatus.IDLE
                )
            }
        }
    }

    private fun getNetworkStatus() {
        viewModelScope.launch {
            connectivityObserver.observe().collect { status ->
                when (status) {
                    ConnectivityStatus.AVAILABLE -> {
                        state = state.copy(
                            status = ConnectivityStatus.AVAILABLE
                        )
                    }
                    ConnectivityStatus.LOSING -> {
                        state = state.copy(
                            status = ConnectivityStatus.LOSING
                        )
                    }
                    ConnectivityStatus.LOST -> {
                        state = state.copy(
                            status = ConnectivityStatus.LOST
                        )
                    }
                    ConnectivityStatus.UNAVAILABLE -> {
                        state = state.copy(
                            status = ConnectivityStatus.UNAVAILABLE
                        )
                    }
                    else -> Unit
                }
            }
        }
    }
}