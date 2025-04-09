package com.example.finanzautotest

import android.app.AlertDialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.iu.adapter.UserAdapter
import com.example.myapplication.iu.model.UserUI

class SwipeToDeleteCallback(
    private val context: Context,
    private val adapter: UserAdapter,
    private val onDeleteConfirmed: (UserUI, Int) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val user = adapter.getUserAt(position)

        AlertDialog.Builder(context)
            .setTitle("Eliminar usuario")
            .setMessage("¿Estás seguro de que deseas eliminar a ${user.firstName} ${user.lastName}?")
            .setPositiveButton("Sí") { _, _ ->
                onDeleteConfirmed(user, position)
            }
            .setNegativeButton("No") { dialog, _ ->
                adapter.notifyItemChanged(position)
                dialog.dismiss()
            }
            .show()
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView
        val icon = ContextCompat.getDrawable(context, R.drawable.ic_delete)!!
        val background = ColorDrawable(Color.parseColor("#FFCDD2"))

        val iconSize = 96
        val iconTop = itemView.top + (itemView.height - iconSize) / 2
        val iconBottom = iconTop + iconSize
        val iconRight = itemView.right - 40
        val iconLeft = iconRight - iconSize

        background.setBounds(
            itemView.right + dX.toInt(),
            itemView.top,
            itemView.right,
            itemView.bottom
        )
        background.draw(c)
        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        icon.draw(c)
    }
}
