package com.example.cataloguser_v_2

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class UserAdapter(
    private val context: Context,
    private val dataSource: ArrayList<User>,
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE
        )
                as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(
            R.layout.list_item_user,
            parent,
            false
        )

        // Get title element
        val titleTextView = rowView.findViewById(
            R.id.user_list_title
        ) as TextView

        // Get subtitle element
        val subtitleTextView = rowView.findViewById(
            R.id.user_list_subtitle
        ) as TextView

        // 1
        val user = getItem(position) as User

        // 2
        titleTextView.text = user.name
        subtitleTextView.text = user.age.toString()

        return rowView
    }

    fun remove(item: Any) {
        dataSource.remove(item)
        notifyDataSetChanged()
    }
}