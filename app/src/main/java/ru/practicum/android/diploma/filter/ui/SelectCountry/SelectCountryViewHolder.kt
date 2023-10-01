package ru.practicum.android.diploma.filter.ui.SelectCountry

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemCountryBinding

class SelectCountryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val binding = ItemCountryBinding.bind(itemView)
    val countryName  = binding.country
    val arrowSelection = binding.arrowSelection
}