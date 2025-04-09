package com.example.myapplication.domain.repository

import com.example.myapplication.data.model.DeleteUserResponseModel
import com.example.myapplication.data.model.RequestUserModel
import com.example.myapplication.data.model.UserModelDto
import com.example.myapplication.data.model.UserResponseModel
import com.example.myapplication.domain.model.User

interface UserRepository {

    suspend fun getAllUser(): List<User>

    suspend fun createUser(requestUserModel: RequestUserModel): UserResponseModel

    suspend fun deleteUser(id:String): DeleteUserResponseModel
}