package ru.practicum.android.diploma.filter.ui.selectindustry

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.domain.models.fields.Industry

class IndustrySelectorAdapter(
    private var industry: List<Industry?>,
    private val onClick: (Industry) -> Unit
) : RecyclerView.Adapter<IndustrySelectorViewHolder>() {

    fun updateIndustry(newIndustry: List<Industry?>) {
        industry = newIndustry
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IndustrySelectorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_regions_industries, parent, false)
        return IndustrySelectorViewHolder(view,onClick)
    }

    override fun getItemCount(): Int {
       return industry.size
    }

    override fun onBindViewHolder(holder: IndustrySelectorViewHolder, position: Int) {
        industry[position]?.let { holder.bind(it) }
    }
}