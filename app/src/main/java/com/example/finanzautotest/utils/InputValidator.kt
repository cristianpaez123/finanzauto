package com.example.myapplication.utils

import android.widget.EditText

object InputValidator {
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validateRequiredFields(vararg fields: EditText): Boolean {
        var isValid = true
        fields.forEach { field ->
            if (field.text.toString().trim().isEmpty()) {
                field.error = "Este campo es obligatorio"
                isValid = false
            }
        }
        return isValid
    }
}