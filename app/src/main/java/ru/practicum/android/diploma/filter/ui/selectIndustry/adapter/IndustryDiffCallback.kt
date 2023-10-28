package ru.practicum.android.diploma.filter.ui.selectIndustry.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.practicum.android.diploma.filter.domain.models.fields.Industry

class IndustryDiffCallback(
    private val oldList: List<Industry?>,
    private val newList: List<Industry?>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldIndustry = oldList[oldItemPosition]
        val newIndustry = newList[newItemPosition]
        return oldIndustry?.id == newIndustry?.id
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldIndustry = oldList[oldItemPosition]
        val newIndustry = newList[newItemPosition]
        return oldIndustry == newIndustry
    }

}