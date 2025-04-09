package com.example.myapplication.iu.state


import com.example.myapplication.iu.model.UserUI

sealed class UserUiState {
    data object Loading : UserUiState()
    data class DataLoaded(val userResponseResult: List<UserUI>) : UserUiState()
    data class Error(val message: String) : UserUiState()
}