package com.joel.data.repo

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DataRepositoryTest {

    @Test
    fun `test save and read preference state`() = runBlocking {
        // Mocking
        val dataStoreRepository = mock<DataStoreRepository>()

        val expectedPreferenceState = true

        whenever(dataStoreRepository.savePreferenceState(true)).thenReturn(Unit)

        val preferenceStateFlow = MutableStateFlow(expectedPreferenceState)
        whenever(dataStoreRepository.readPreferenceState()).thenReturn(preferenceStateFlow)

        dataStoreRepository.savePreferenceState(true)
        verify(dataStoreRepository).savePreferenceState(true)

        val actualPreferenceState = dataStoreRepository.readPreferenceState().first()

        assertEquals(expectedPreferenceState, actualPreferenceState)
    }

    @Test
    fun `test save and read onboarding state`() = runBlocking {
        // Mocking
        val dataStoreRepository = mock<DataStoreRepository>()

        val expectedOnboardingState = true

        whenever(dataStoreRepository.saveOnboardingState(true)).thenReturn(Unit)

        val onboardingStateFlow = MutableStateFlow(expectedOnboardingState)
        whenever(dataStoreRepository.readOnboardingState()).thenReturn(onboardingStateFlow)

        dataStoreRepository.saveOnboardingState(true)
        verify(dataStoreRepository).saveOnboardingState(true)

        val actualPreferenceState = dataStoreRepository.readOnboardingState().first()

        assertEquals(expectedOnboardingState, actualPreferenceState)
    }
}
