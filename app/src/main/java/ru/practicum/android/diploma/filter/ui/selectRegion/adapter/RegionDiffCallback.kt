package ru.practicum.android.diploma.filter.ui.selectRegion.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.practicum.android.diploma.search.domain.models.fields.Area

class RegionDiffCallback(
    private val oldList: List<Area?>,
    private val newList: List<Area?>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldRegion = oldList[oldItemPosition]
        val newRegion = newList[newItemPosition]
        return oldRegion?.id == newRegion?.id
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldRegion = oldList[oldItemPosition]
        val newRegion = newList[newItemPosition]
        return oldRegion == newRegion
    }
}