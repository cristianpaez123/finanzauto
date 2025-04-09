package com.example.myapplication.data.model

import com.example.myapplication.domain.model.User
data class UserModelDto(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String
)

fun List<UserModelDto>.toDomainEntity(): List<User> {
    return this.map {
        it.toDomainEntity()
    }
}
fun UserModelDto.toDomainEntity(): User {
    return User(
        id = id,
        title = title,
        firstName = firstName,
        lastName = lastName,
        picture = picture
    )
}


