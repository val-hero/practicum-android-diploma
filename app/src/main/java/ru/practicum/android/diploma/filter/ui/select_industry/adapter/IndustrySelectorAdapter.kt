package ru.practicum.android.diploma.filter.ui.select_industry

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.DiffUtil
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.domain.models.fields.Industry

class IndustrySelectorAdapter(
    private var industry: List<Industry?>,
    private val onClick: (Industry) -> Unit,
    private val editText: EditText
) : RecyclerView.Adapter<IndustrySelectorViewHolder>() {

    private var filtredIndustries = industry
    private var selectedIndustry: Industry? = null
    private var selectedPosition: Int = RecyclerView.NO_POSITION
    private var isFilteringInProgress = false

    init {
        editText.doOnTextChanged { text, _, _, _ ->
            filter(text.toString())
        }
    }

    private fun filter(query: String) {
        isFilteringInProgress = true

        val oldFilteredIndustries = filtredIndustries

        filtredIndustries = if (query.isEmpty()) {
            industry
        } else {
            industry.filter { it?.name!!.contains(query, ignoreCase = true) }
        }

        val diffCallback = IndustryDiffCallback(oldFilteredIndustries, filtredIndustries)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)

        isFilteringInProgress = false
    }

    fun updateIndustry(newIndustry: List<Industry?>) {
        val diffCallback = IndustryDiffCallback(industry, newIndustry)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        industry = newIndustry
        filter(editText.text.toString())
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IndustrySelectorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_regions_industries, parent, false)
        return IndustrySelectorViewHolder(view, onClick)
    }

    override fun getItemCount(): Int {
        return filtredIndustries.size
    }

    fun getSelectedPosition(): Int {
        return selectedPosition
    }

    private fun selectedIndustry(
        holder: IndustrySelectorViewHolder,
        position: Int
    ) {
        if (selectedPosition != position) {
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged()
            onClick(filtredIndustries[position]!!)
        }
    }

    override fun onBindViewHolder(
        holder: IndustrySelectorViewHolder,
        position: Int
    ) {
        filtredIndustries[position]?.let { holder.bind(it) }
        holder.radioButton.isChecked = selectedIndustry == filtredIndustries[position]
        holder.itemView.setOnClickListener {
            selectedIndustry(holder,position)
        }
        holder.radioButton.setOnClickListener {
            selectedIndustry(holder,position)
        }
        holder.radioButton.isChecked = selectedPosition == position
    }
}