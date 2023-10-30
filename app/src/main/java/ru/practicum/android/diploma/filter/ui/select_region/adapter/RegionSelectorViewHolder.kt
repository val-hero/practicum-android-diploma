package ru.practicum.android.diploma.filter.ui.select_region.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemCountryBinding
import ru.practicum.android.diploma.search.domain.models.fields.Area

class RegionSelectorViewHolder(itemView: View, val onClick: (Area) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    val binding = ItemCountryBinding.bind(itemView)
    val regionName = binding.country

    fun bind(region: Area) {
        regionName.text = region.name
        itemView.setOnClickListener {
            onClick(region)
        }
    }
}