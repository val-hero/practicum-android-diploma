package ru.practicum.android.diploma.filter.data.network.dto.feilds

import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.filter.domain.models.fields.Industry

data class IndustryDto(
    val id: String,
    val name: String,
    val parentId: String?
){
    fun toIndustry(): Industry {
        return Industry(
            id = this.id,
            name = this.name,
            parentId = this.parentId
        )
    }
}
