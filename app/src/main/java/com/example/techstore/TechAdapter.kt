package com.example.techstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class TechAdapter(private val techs: List<Tech>) : RecyclerView.Adapter<TechAdapter.TechViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tech_item, parent, false)
        return TechViewHolder(view)
    }

    override fun onBindViewHolder(holder: TechViewHolder, position: Int) {
        val tech = techs[position]
        holder.nameTextView.text = tech.name
        holder.brandTextView.text = tech.brand
        holder.sizeTextView.text = tech.count.toString()
        holder.priceTextView.text = tech.price.toString()
    }

    override fun getItemCount(): Int = techs.size

    fun getTechAt(position: Int): Tech? {
        return if (position in 0 until techs.size) techs[position] else null
    }

    inner class TechViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tech_name)
        val brandTextView: TextView = itemView.findViewById(R.id.tech_brand)
        val sizeTextView: TextView = itemView.findViewById(R.id.tech_size)
        val priceTextView: TextView = itemView.findViewById(R.id.tech_price)
    }
}
