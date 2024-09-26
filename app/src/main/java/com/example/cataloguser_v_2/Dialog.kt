package com.example.cataloguser_v_2

import android.content.Context
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog

class Dialog {
    companion object {
        fun createDialogDelete(context: Context, adapter: UserAdapter) =
            AdapterView.OnItemClickListener { parent, v, position, id ->
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Внимание")
                .setMessage("Удалить елемент списка?")
                .setCancelable(true)
                .setNegativeButton("Нет") { dialog, whith ->
                    dialog.cancel()
                }
                .setPositiveButton("Да") { dialog, whith ->
                    adapter.remove(adapter.getItem(position))
                }.create()
            builder.show()
        }
    }
}