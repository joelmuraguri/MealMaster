package com.joel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joel.database.dao.UserDao
import com.joel.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class MealMasterDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}