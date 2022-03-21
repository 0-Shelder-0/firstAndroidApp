package com.example.myapplication.models

class Tariff(private val name: String, private val info: String) {
    fun getName(): String = name;
    fun getInfo(): String = info;
}