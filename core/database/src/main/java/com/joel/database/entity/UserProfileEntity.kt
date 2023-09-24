package com.joel.database.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.joel.database.converters.StringListConverter
import com.joel.database.converters.UriTypeConverter

@Entity(tableName = "User_Profile_Table")
data class UserEntity(
    @PrimaryKey val name : String,
    @TypeConverters(StringListConverter::class)
    val diet : List<String>,
    @TypeConverters(StringListConverter::class)
    val allergies : List<String>,
    @TypeConverters(StringListConverter::class)
    val nutrients : List<String>,
    @TypeConverters(UriTypeConverter::class)
    val profileUrl : Uri,
)
