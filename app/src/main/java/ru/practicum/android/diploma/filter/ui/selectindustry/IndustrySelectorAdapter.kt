package ru.practicum.android.diploma.filter.ui.selectindustry

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.domain.models.fields.Industry

class IndustrySelectorAdapter(
    private var industry: List<Industry?>,
    private val onClick: (Industry) -> Unit,
    private val editText: EditText
) : RecyclerView.Adapter<IndustrySelectorViewHolder>() {

    private var filtredIndustries = industry

    init {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filter(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun filter(query: String) {
        filtredIndustries = if (query.isEmpty()) {
            industry
        } else {
            industry.filter { it?.name!!.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    private var selectedPosition = RecyclerView.NO_POSITION

    fun updateIndustry(newIndustry: List<Industry?>) {
        industry = newIndustry
        filter(editText.text.toString())
        notifyDataSetChanged()
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

    override fun onBindViewHolder(
        holder: IndustrySelectorViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        filtredIndustries[position]?.let { holder.bind(it) }
        holder.radioButton.isChecked = selectedPosition == position
        holder.radioButton.setOnClickListener {
            if (selectedPosition != position) {
                notifyItemChanged(selectedPosition)
                selectedPosition = position
                notifyItemChanged(selectedPosition)
            }
        }
    }
}