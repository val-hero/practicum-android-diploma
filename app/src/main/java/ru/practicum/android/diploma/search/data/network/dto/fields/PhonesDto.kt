package ru.practicum.android.diploma.search.data.network.dto.fields

import ru.practicum.android.diploma.search.domain.models.fields.Phones

data class PhonesDto(
    val city: String?,
    val country: String?,
    val number: String?
) {
    fun toPhones(): Phones {
        return Phones(
            city = this.city,
            country = this.country,
            number = this.number
        )
    }
}
