package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class AccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.account_fragment, container, false);
        val sumDebt: TextView = view.findViewById(R.id.sum_debt)
        val accountNumber: TextView = view.findViewById(R.id.account_number)
        val balance: TextView = view.findViewById(R.id.balance)

        sumDebt.text = "200.00"
        accountNumber.text = "111001020210"
        balance.text = "532.12"

        return view
    }
}