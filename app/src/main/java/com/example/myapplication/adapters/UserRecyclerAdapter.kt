package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.UserInfo
import com.example.myapplication.models.UserModel

class UserRecyclerAdapter(
    private val userInfoList: List<UserModel>
) : RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userIconView: ImageView = itemView.findViewById(R.id.user_icon)
        val userInfoView: TextView = itemView.findViewById(R.id.user_info)
        val line: View = itemView.findViewById(R.id.user_line)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userInfoView.text = userInfoList[position].getInfo()
        holder.userIconView.setImageResource(userInfoList[position].getIconId())

        if (position == 0) {
            holder.line.isVisible = false
        }
    }

    override fun getItemCount(): Int = userInfoList.size
}