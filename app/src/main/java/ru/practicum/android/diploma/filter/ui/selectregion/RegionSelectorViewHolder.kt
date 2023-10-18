package ru.practicum.android.diploma.filter.ui.selectregion

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemRegionsIndustriesBinding
import ru.practicum.android.diploma.search.domain.models.fields.Area

class RegionSelectorViewHolder(itemView: View, val onClick: (Area) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    val binding = ItemRegionsIndustriesBinding.bind(itemView)
    val regionAndIndustryName = binding.regionsAndIndustry

    fun bind(region: Area) {
        regionAndIndustryName.text = region.name
        itemView.setOnClickListener {
            onClick(region)
        }
    }
}