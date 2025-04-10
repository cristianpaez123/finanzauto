package com.example.myapplication.data.network

import com.example.myapplication.data.model.Data
import com.example.myapplication.data.model.DeleteUserResponseModel
import com.example.myapplication.data.model.RequestUserModel
import com.example.myapplication.data.model.UserModelDto
import com.example.myapplication.data.model.UserResponseModel
import com.example.myapplication.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(private val api: UserApi) {

    suspend fun getAllUser(): List<UserModelDto> {
        return withContext(Dispatchers.IO) {
            api.getAllUser().data
        }
    }

    suspend fun createUser(requestUserModel: RequestUserModel): UserResponseModel{
        return withContext(Dispatchers.IO){
            val response = api.createUser(requestUserModel)
            response
        }
    }

    suspend fun deleteUser(id:String): DeleteUserResponseModel{
        return withContext(Dispatchers.IO){
            val response = api.deleteUser(id)
            response
        }
    }
}