package com.example.myapplication.data.model

data class Data(
    val data : List<UserModelDto>,
    val total : Int,
    val page : Int,
    val limit : Int,
)
