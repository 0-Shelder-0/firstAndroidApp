package com.example.myapplication.models

class Tariff(private val name: String, private val info: String, private val cost: String) {
    fun getName(): String = name;
    fun getInfo(): String = info;
    fun getCost(): String = cost;
}