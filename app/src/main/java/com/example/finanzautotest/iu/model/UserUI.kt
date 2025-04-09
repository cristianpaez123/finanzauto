package com.example.myapplication.iu.model

import android.os.Parcelable
import com.example.myapplication.domain.model.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUI(
    val id: String,
    val title: String?,
    val firstName: String,
    val lastName: String,
    val picture: String
) : Parcelable

fun User.toUserUI(): UserUI {
    return UserUI(
        id = id,
        title = title,
        firstName = firstName,
        lastName = lastName,
        picture = picture
    )
}
