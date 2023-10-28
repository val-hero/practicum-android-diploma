package ru.practicum.android.diploma.filter.ui.selectCountry.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.practicum.android.diploma.filter.domain.models.fields.Country

class CountryDiffCallback(
    private val oldList: List<Country?>,
    private val newList: List<Country?>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldCountry = oldList[oldItemPosition]
        val newCountry= newList[newItemPosition]
        return oldCountry?.id == newCountry?.id
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldCountry = oldList[oldItemPosition]
        val newCountry = newList[newItemPosition]
        return oldCountry == newCountry
    }

}