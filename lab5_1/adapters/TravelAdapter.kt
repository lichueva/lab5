package com.example.lab5_1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5_1.Travel
import com.example.lab5_1.R


class TravelAdapter(private val travels: List<Travel>, private val onItemClick: (Travel) -> Unit) :
    RecyclerView.Adapter<TravelAdapter.TravelViewHolder>() {

    inner class TravelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.travel_title)
        private val travelDate: TextView = itemView.findViewById(R.id.travel_date)

        fun bind(travel: Travel) {
            title.text = travel.place
            travelDate.text = travel.dateTravel
            itemView.setOnClickListener { onItemClick(travel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return TravelViewHolder(view)
    }

    override fun onBindViewHolder(holder: TravelViewHolder, position: Int) {
        holder.bind(travels[position])
    }

    override fun getItemCount(): Int = travels.size
}