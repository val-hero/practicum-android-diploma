package ru.practicum.android.diploma.filter.ui.select_country.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemCountryBinding
import ru.practicum.android.diploma.filter.domain.models.fields.Country

class SelectCountryViewHolder(itemView: View, val onClick: (Country) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    val binding = ItemCountryBinding.bind(itemView)
    val countryName = binding.country

    fun bind(country: Country) {
        countryName.text = country.name
        itemView.setOnClickListener {
            onClick(country)
        }
    }
}

