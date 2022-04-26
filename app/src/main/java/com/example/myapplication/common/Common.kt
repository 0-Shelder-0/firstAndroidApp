package com.example.myapplication.common

import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.retrofit.RetrofitServices

object Common {
    private const val BASE_URL = "https://61f5894b62f1e300173c41ba.mockapi.io/"

    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}