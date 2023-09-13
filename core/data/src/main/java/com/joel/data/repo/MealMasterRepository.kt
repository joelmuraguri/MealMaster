package com.joel.data.repo

import com.joel.database.dao.UserDao
import javax.inject.Inject

class MealMasterRepository @Inject constructor(
    private val dao: UserDao
) : UserInfoRepository {

    override fun getUserInfo() {
        return
    }

    override fun saveUserInfo() {
        TODO("Not yet implemented")
    }
}