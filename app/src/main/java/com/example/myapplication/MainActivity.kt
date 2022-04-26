package com.example.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.TariffRecyclerAdapter
import com.example.myapplication.adapters.UserRecyclerAdapter
import com.example.myapplication.common.Common
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.*
import com.example.myapplication.retrofit.RetrofitServices
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retrofitServices: RetrofitServices
    lateinit var userRecyclerAdapter: UserRecyclerAdapter
    lateinit var tariffRecyclerAdapter: TariffRecyclerAdapter
    lateinit var binding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tariffs: MutableList<TariffModel> = mutableListOf()
        addTariffView(tariffs)

        val userData = getUserInfoList()
        addUserView(userData)

        retrofitServices = Common.retrofitService
        loadData(userData, tariffs)
    }

    private fun addTariffView(tariffs: List<TariffModel>) {
        tariffRecyclerAdapter = TariffRecyclerAdapter(tariffs)

        val tariffRecyclerView: RecyclerView = findViewById(R.id.tariffRecyclerView)
        tariffRecyclerView.layoutManager = LinearLayoutManager(this)
        tariffRecyclerView.adapter = tariffRecyclerAdapter
    }

    private fun addUserView(userData: List<UserModel>) {
        userRecyclerAdapter = UserRecyclerAdapter(userData)

        val userRecyclerView: RecyclerView = findViewById(R.id.userRecyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = userRecyclerAdapter
    }

    private fun loadData(userData: MutableList<UserModel>, tariffs: MutableList<TariffModel>) {
        MainScope().launch {
            retrofitServices.getUserInfo().enqueue(object : Callback<List<UserInfo>> {
                override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<UserInfo>>,
                    response: Response<List<UserInfo>>
                ) {
                    val userList = response.body() as List<UserInfo>
                    val user = userList.first()

                    userData[0].setInfo("${user.firstName} ${user.lastName}")
                    userData[1].setInfo(user.address!!)

                    userRecyclerAdapter.notifyDataSetChanged()
                }
            })

            retrofitServices.getTariffs().enqueue(object : Callback<List<Tariff>> {
                override fun onFailure(call: Call<List<Tariff>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<Tariff>>,
                    response: Response<List<Tariff>>
                ) {
                    val tariffList = (response.body() as List<Tariff>).map(::mapTariffToModel)
                    tariffs.clear()
                    tariffs.addAll(tariffList)

                    tariffRecyclerAdapter.notifyDataSetChanged()
                }
            })

            retrofitServices.getBalance().enqueue(object : Callback<List<Balance>> {
                override fun onFailure(call: Call<List<Balance>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<Balance>>,
                    response: Response<List<Balance>>
                ) {
                    val balanceList = response.body() as List<Balance>
                    val balance = balanceList.first()

                    dataModel.message.value = balance
                }
            })
        }
    }

    private fun mapTariffToModel(tariff: Tariff) =
        TariffModel(
            tariff.title!!,
            tariff.desc!!,
            (tariff.cost ?: 0).toString()
        )

    private fun getUserInfoList(): MutableList<UserModel> {
        val userInfoList = this.resources.getStringArray(R.array.userInfoList).toList()
        val userIcons = this.resources.getStringArray(R.array.userIcons).toList()
        val results: MutableList<UserModel> = mutableListOf()

        for ((index, info) in userInfoList.withIndex()) {
            if (userIcons.size > index) {
                val imageName = userIcons[index]
                val iconId = resources.getIdentifier(
                    "com.example.myapplication:drawable/$imageName",
                    null,
                    null
                )

                if (iconId != 0) {
                    val userInfo = UserModel(info, iconId)
                    results.add(userInfo)
                }
            }
        }

        return results
    }
}