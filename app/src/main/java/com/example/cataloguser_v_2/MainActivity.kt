package com.example.cataloguser_v_2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

//    private var names = mutableListOf<String>()

    //    private var ages = mutableListOf<Int>()
    private lateinit var users: CatalogUsers
    private var adapter: UserAdapter? = null

    private lateinit var toolbar: Toolbar
    private lateinit var name: EditText
    private lateinit var age: EditText
    private lateinit var save: Button
    private lateinit var listLV: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        users = ViewModelProvider(this)[CatalogUsers::class.java]
        toolbar = findViewById(R.id.toolbar_main)
        name = findViewById(R.id.nameET)
        age = findViewById(R.id.ageET)
        save = findViewById(R.id.saveBTN)
        listLV = findViewById(R.id.listLV)

        setSupportActionBar(toolbar)

        /**
        Как тут добавить Sublist, что бы возраст был под именем?
         */
        adapter = UserAdapter(this, users.listUser)
        listLV.adapter = adapter

        listLV.onItemClickListener =
            Dialog.createDialogDelete(this, adapter!!)
    }

//    private fun removeElement(position: Int) {
//        adapter!!.remove(adapter!!.getItem(position))
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit -> {
                finish()
            }
        }
        return true
    }

    fun saveOnClick(view: View) {
        if (!checked()) {
            users.addUser(User(name.text.toString(), age.text.toString().toInt()))
//                names.add(name.text.toString())
//                ages.add(age.text.toString().toInt())
            adapter!!.notifyDataSetChanged()
            default()
        }
    }

    private fun checked(): Boolean {
        if (name.text.isEmpty()) {
            name.setHintTextColor(ContextCompat.getColor(this, R.color.red))
            name.hint = "Поле не может быть пустым"
        }
        if (age.text.isEmpty()) {
            age.setHintTextColor(ContextCompat.getColor(this, R.color.red))
            age.hint = "Поле не может быть пустым"
        }
        if (name.text.isEmpty() || age.text.isEmpty()) {
            return true
        }
        return false
    }

    private fun default() {
        defaultEditText(name, R.string.inputName)
        defaultEditText(age, R.string.inputAge)
    }

    private fun defaultEditText(editText: EditText, colorID: Int, textID: Int) {
        editText.text.clear()
        editText.setHintTextColor(ContextCompat.getColor(this, colorID))
        editText.hint = ContextCompat.getString(this, textID)
    }

    private fun defaultEditText(editText: EditText, textID: Int) {
        defaultEditText(editText, R.color.gray, textID)
    }
}