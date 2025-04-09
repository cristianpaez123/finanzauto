package com.example.myapplication.iu.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzautotest.R
import com.example.finanzautotest.databinding.ItemUserBinding
import com.example.myapplication.iu.model.UserUI
import com.squareup.picasso.Picasso

class UserAdapter(
    private val onUserClickListener: OnUserClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    lateinit var context: Context
    private val dataUser: MutableList<UserUI> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setUser(context: Context, dataUser: List<UserUI>) {
        this.dataUser.clear()
        this.dataUser.addAll(dataUser)
        this.context = context
        notifyDataSetChanged()
    }

    fun getUserAt(position: Int): UserUI = dataUser[position]

    fun removeUserAt(position: Int) {
        dataUser.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemUserBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = dataUser[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return dataUser.size
    }


    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserUI) {
            binding.user = user

            binding.textName.text = user.firstName
            binding.lastName.text = user.lastName
            binding.userID.text = "ID: ${user.id}"

            if (user.picture.isNotBlank()) {
                Picasso.get().load(user.picture).into(binding.imgUser)
            }
        }
    }
    interface OnUserClickListener {
        fun onUserClicked(id: String)
    }
}