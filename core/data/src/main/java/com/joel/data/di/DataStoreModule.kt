package com.joel.data.di

import android.content.Context
import com.joel.data.repo.DataRepositoryImpl
import com.joel.data.repo.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context,
    ) : DataStoreRepository{
        return DataRepositoryImpl(context)
    }

}