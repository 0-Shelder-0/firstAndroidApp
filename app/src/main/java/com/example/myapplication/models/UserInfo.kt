package com.example.myapplication.models

class UserInfo(private val info: String, private val iconId: Int) {
    fun getInfo(): String = info
    fun getIconId(): Int = iconId
}