package com.joel.data.di

import com.joel.database.MealMasterDatabase
import com.joel.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideUserDao(db : MealMasterDatabase) : UserDao = db.userDao()
}