package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.myapplication.DataModel
import com.example.myapplication.R
import com.example.myapplication.databinding.AccountFragmentBinding
import com.example.myapplication.databinding.ActivityMainBinding

class AccountFragment : Fragment() {
    lateinit var binding: AccountFragmentBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AccountFragmentBinding.inflate(layoutInflater)
        dataModel.message.observe(activity as LifecycleOwner) {
            binding.sumDebt.text = (it.nextPay ?: 0).toString()
            binding.accountNumber.text = (it.accNum ?: 0).toString()
            binding.balance.text = (it.balance ?: 0).toString()
        }
        return binding.root
    }
}