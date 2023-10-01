package ru.practicum.android.diploma.filter.ui.SelectCountry

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemCountryBinding

class SelectCountryViewHolder(itemView: View, val onClick: (String) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    val binding = ItemCountryBinding.bind(itemView)
    val countryName = binding.country

    fun bind(country: String) {
        countryName.text = country
        itemView.setOnClickListener {
            onClick(country)
        }
    }
}

