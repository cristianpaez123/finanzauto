package com.example.myapplication.iu.helper

import com.example.finanzautotest.databinding.FragmentListUserBinding
import com.example.myapplication.iu.state.UserUiState
import com.example.myapplication.utils.ContextExtensions.toast
import com.example.myapplication.utils.ViewExtensions.hide
import com.example.myapplication.utils.ViewExtensions.show

class UserListUiStateHandler(
    private val binding: FragmentListUserBinding?,
    private val listHelper: UserListViewHelper
) {
    fun render(state: UserUiState) {
        when (state) {
            is UserUiState.Loading -> {
                binding?.progressLoading?.show()
            }

            is UserUiState.DataLoaded -> {
                binding?.progressLoading?.hide()
                listHelper.updateUser(state.userResponseResult)
            }

            is UserUiState.Error -> {
                binding?.progressLoading?.hide()
                binding?.root?.context?.toast(state.message)
            }
        }
    }
}