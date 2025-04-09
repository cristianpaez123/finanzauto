package com.example.myapplication.iu.helper

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.iu.adapter.UserAdapter
import com.example.myapplication.iu.model.UserUI

class UserListViewHelper(
    private val recyclerView: RecyclerView?,
    private val context: Context,
    private val listener: UserAdapter.OnUserClickListener
) {
    val adapter = UserAdapter(listener)

    fun setUp() {
        recyclerView?.layoutManager = GridLayoutManager(this.context, 1)
        recyclerView?.adapter = adapter
    }

    fun updateUser(users: List<UserUI>) {
        adapter.setUser(context, users)
    }
}