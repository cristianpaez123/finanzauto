package com.example.myapplication.data.network

import com.example.myapplication.data.model.DeleteUserResponseModel
import com.example.myapplication.data.model.RequestUserModel
import com.example.myapplication.data.model.UserResponseModel
import com.example.myapplication.data.model.toDomainEntity
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UserService) : UserRepository {

    override suspend fun getAllUser(): List<User> = api.getAllUser().toDomainEntity()

    override suspend fun createUser(requestUserModel: RequestUserModel): UserResponseModel = api.createUser(requestUserModel)

    override suspend fun deleteUser(id:String): DeleteUserResponseModel = api.deleteUser(id)

}