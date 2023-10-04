package ru.practicum.android.diploma.filter.domain.models.fields

import ru.practicum.android.diploma.filter.data.network.dto.feilds.CountryDto

data class Country(
    val id: Long?,
    val name: String?
) {
    fun toDto(): CountryDto {
        return CountryDto(
            id = this.id,
            name = this.name
        )
    }
}