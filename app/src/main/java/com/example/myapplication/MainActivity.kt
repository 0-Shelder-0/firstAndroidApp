package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.TariffRecyclerAdapter
import com.example.myapplication.adapters.UserRecyclerAdapter
import com.example.myapplication.models.Tariff
import com.example.myapplication.models.UserInfo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tariffRecyclerView: RecyclerView = findViewById(R.id.tariffRecyclerView)
        tariffRecyclerView.layoutManager = LinearLayoutManager(this)
        tariffRecyclerView.adapter = TariffRecyclerAdapter(getTariffs())

        val userRecyclerView: RecyclerView = findViewById(R.id.userRecyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = UserRecyclerAdapter(getUserInfoList())
    }

    private fun getTariffs(): List<Tariff> {
        val tariffNames = this.resources.getStringArray(R.array.tariffNames).toList()
        val tariffInfoList = this.resources.getStringArray(R.array.tariffInfoList).toList()
        val results: MutableList<Tariff> = mutableListOf()

        for ((index, name) in tariffNames.withIndex()) {
            if (tariffInfoList.size > index) {
                val tariff = Tariff(name, tariffInfoList[index])
                results.add(tariff)
            }
        }

        return results.toList()
    }

    private fun getUserInfoList(): List<UserInfo> {
        val userInfoList = this.resources.getStringArray(R.array.userInfoList).toList()
        val userIcons = this.resources.getStringArray(R.array.userIcons).toList()
        val results: MutableList<UserInfo> = mutableListOf()

        for ((index, info) in userInfoList.withIndex()) {
            if (userIcons.size > index) {
                val imageName = userIcons[index]
                val iconId = resources.getIdentifier("com.example.myapplication:drawable/$imageName", null, null)

                if (iconId != 0) {
                    val userInfo = UserInfo(info, iconId)
                    results.add(userInfo)
                }
            }
        }

        return results.toList()
    }
}