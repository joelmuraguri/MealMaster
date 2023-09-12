package com.joel.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.joel.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM User_Profile_Table")
    fun getUserInfo() : Flow<UserEntity>

    @Upsert
    fun updateUserInfo(userEntity: UserEntity)

}