package ru.practicum.android.diploma.filter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemRegionsIndustriesBinding

class IndustryAndRegionSelectorViewHolder(itemView: View, val onClick: (String) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

        val binding = ItemRegionsIndustriesBinding.bind(itemView)

}