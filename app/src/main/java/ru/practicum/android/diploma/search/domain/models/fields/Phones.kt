package ru.practicum.android.diploma.search.domain.models.fields

import ru.practicum.android.diploma.search.data.network.dto.fields.PhonesDto

data class Phones(
    val city: String?,
    val country: String?,
    val number: String?
) {
    fun toDto(): PhonesDto {
        return PhonesDto(
            city = this.city,
            country = this.country,
            number = this.number
        )
    }
}