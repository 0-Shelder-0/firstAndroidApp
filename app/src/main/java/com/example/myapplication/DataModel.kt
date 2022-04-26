package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Balance

class DataModel : ViewModel() {
    val message: MutableLiveData<Balance> by lazy {
        MutableLiveData<Balance>()
    }
}