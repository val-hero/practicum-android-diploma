package ru.practicum.android.diploma.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R

class IndustryAndRegionSelectorAdapter(
    private var regionOrIndustry: List<String?>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<IndustryAndRegionSelectorViewHolder>() {

    fun updateRegionOrIndustry(newRegionOrIndustry: List<String?>) {
        regionOrIndustry= newRegionOrIndustry
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IndustryAndRegionSelectorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_regions_industries, parent, false)
        return IndustryAndRegionSelectorViewHolder(view,onClick)
    }

    override fun getItemCount(): Int {
       return regionOrIndustry.size
    }

    override fun onBindViewHolder(holder: IndustryAndRegionSelectorViewHolder, position: Int) {
        regionOrIndustry[position]?.let { holder.bind(it) }
    }
}