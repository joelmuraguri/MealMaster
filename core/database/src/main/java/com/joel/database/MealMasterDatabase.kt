package com.joel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.joel.database.converters.StringListConverter
import com.joel.database.converters.UriTypeConverter
import com.joel.database.dao.UserDao
import com.joel.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class, UriTypeConverter::class)
abstract class MealMasterDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}