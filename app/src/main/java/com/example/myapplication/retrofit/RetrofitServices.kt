package com.example.myapplication.retrofit

import com.example.myapplication.models.Balance
import com.example.myapplication.models.Tariff
import com.example.myapplication.models.UserInfo
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("userInfo")
    fun getUserInfo(): Call<List<UserInfo>>

    @GET("balance")
    fun getBalance(): Call<List<Balance>>

    @GET("tariffs")
    fun getTariffs(): Call<List<Tariff>>
}