package ru.practicum.android.diploma.filter.ui.SelectCountry

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R

class SelectCountryAdapter(private val countries: Array<String>):RecyclerView.Adapter<SelectCountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectCountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false)
        return SelectCountryViewHolder(view)
    }

    override fun getItemCount(): Int {
       return countries.size
    }

    override fun onBindViewHolder(holder: SelectCountryViewHolder, position: Int) {
        holder.countryName.text = countries[position]
    }
}