package com.joel.data.repo

import com.joel.data.models.ConnectivityStatus
import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<ConnectivityStatus>
}