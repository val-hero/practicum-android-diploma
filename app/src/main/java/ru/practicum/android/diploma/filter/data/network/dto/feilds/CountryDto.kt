package ru.practicum.android.diploma.filter.data.network.dto.feilds

import ru.practicum.android.diploma.filter.domain.models.fields.Country

data class CountryDto(
    val id: Long?,
    val name: String?
) {
    fun toCountry(): Country {
        return Country(
            id = this.id,
            name = this.name
        )
    }
}