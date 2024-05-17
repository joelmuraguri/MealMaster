package com.joel.mealmaster.utils.connectivity

import com.joel.data.models.ConnectivityStatus


data class ConnectivityState(
    val status: ConnectivityStatus = ConnectivityStatus.IDLE
)
