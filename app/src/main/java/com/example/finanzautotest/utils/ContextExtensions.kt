package com.example.myapplication.utils

import android.content.Context
import android.widget.Toast

object ContextExtensions {
    fun Context.toast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

}