package com.example.cataloguser_v_2

import androidx.lifecycle.ViewModel

class CatalogUsers: ViewModel(){
    var listUser = arrayListOf<User>()
    fun addUser(user: User){
        listUser.add(user)
    }
}