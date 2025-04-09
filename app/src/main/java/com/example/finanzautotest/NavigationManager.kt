package com.example.finanzautotest

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

object NavigationManager {
    fun goToRegisterUser(fragment: Fragment){
        fragment.findNavController().navigate(R.id.action_listUserFragment_to_RegisterUserFragment)
    }
}