package com.example.myapplication.models

class TariffModel(private val name: String, private val info: String, private val cost: String) {
    fun getName(): String = name
    fun getInfo(): String = info
    fun getCost(): String = cost
}