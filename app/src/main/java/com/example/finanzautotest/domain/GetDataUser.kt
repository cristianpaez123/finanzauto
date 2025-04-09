package com.example.myapplication.domain

import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class GetDataUser @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(): List<User> = repository.getAllUser()
}