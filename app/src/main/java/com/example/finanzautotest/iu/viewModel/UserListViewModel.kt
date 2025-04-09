package com.example.myapplication.iu.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.DeleteUser
import com.example.myapplication.domain.GetDataUser
import com.example.myapplication.iu.model.toUserUI
import com.example.myapplication.iu.state.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getDataUser: GetDataUser,
    private val deleteUserUseCase: DeleteUser,
) : ViewModel() {

    private val dataUserState: MutableLiveData<UserUiState> = MutableLiveData()
    fun getDataUserState(): LiveData<UserUiState> = dataUserState

    init {
        loadData()
    }

    fun loadData() {
        dataUserState.value = UserUiState.Loading
        viewModelScope.launch {
            try {
                val userData = getDataUser()
                dataUserState.value = UserUiState.DataLoaded(userData.map { it.toUserUI() })
            } catch (e: Exception) {
                dataUserState.value = UserUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteUser(id: String) {
        dataUserState.value = UserUiState.Loading
        viewModelScope.launch {
            try {
                deleteUserUseCase(id)
                loadData()
            } catch (e: Exception) {
                dataUserState.value = UserUiState.Error(e.message ?: "Failed to delete user")
            }
        }
    }
}
