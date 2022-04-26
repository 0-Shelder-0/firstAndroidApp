package com.example.myapplication.models

class UserModel(private var info: String, private val iconId: Int) {
    fun getInfo(): String = info

    fun setInfo(newInfo: String) {
        info = newInfo
    }

    fun getIconId(): Int = iconId
}