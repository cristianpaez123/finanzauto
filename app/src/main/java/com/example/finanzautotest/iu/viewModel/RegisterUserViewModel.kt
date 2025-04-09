package com.example.myapplication.iu.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.RequestUserModel
import com.example.myapplication.data.model.UserResponseModel
import com.example.myapplication.domain.CreateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val createUser: CreateUser
) : ViewModel() {

    private val registerUserState: MutableLiveData<RegisterUserState> = MutableLiveData()
    fun registerUserStateUserState(): LiveData<RegisterUserState> = registerUserState

   suspend fun createUser(
        title: String,
        firstName: String,
        lastName: String,
        gender: String,
        email: String,
        dateOfBirth: String,
        phone: String
    ) {
        val newUser = RequestUserModel(
            title = title,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            email = email,
            dateOfBirth = dateOfBirth,
            phone = phone,
            picture = "https://randomuser.me/api/portraits/med/men/80.jpg"
        )

        viewModelScope.launch {
            try {
                registerUserState.postValue(
                    newUser?.let {
                        createUser.invoke(
                            it
                        )
                    }?.let {
                        RegisterUserState.DataLoaded(
                            it
                        )
                    }
                )
            } catch (e: Exception) {
                registerUserState.postValue(RegisterUserState.Error("ERROR"))
            }
        }
    }
    sealed class RegisterUserState {
        data class DataLoaded(val userResponseResult: UserResponseModel) : RegisterUserState()
        data class Error(val message: String) : RegisterUserState()
    }
}
