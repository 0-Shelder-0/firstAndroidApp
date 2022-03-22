package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Tariff

class TariffRecyclerAdapter(private val tariffs: List<Tariff>) : RecyclerView.Adapter<TariffRecyclerAdapter.TariffViewHolder>() {

    class TariffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tariffNameView: TextView = itemView.findViewById(R.id.tariffName)
        val tariffInfoView: TextView = itemView.findViewById(R.id.tariffInfo)
        val tariffCost: TextView = itemView.findViewById(R.id.tariff_cost)
        val line: View = itemView.findViewById(R.id.line)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TariffViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return TariffViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TariffViewHolder, position: Int) {
        holder.tariffNameView.text = tariffs[position].getName()
        holder.tariffInfoView.text = tariffs[position].getInfo()
        holder.tariffCost.text = tariffs[position].getCost()

        if (position == 0) {
            holder.line.isVisible = false
        }
    }

    override fun getItemCount(): Int = tariffs.size
}