package ru.practicum.android.diploma.search.data.network.dto.fields

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.search.domain.models.fields.Phones

data class PhonesDto(
    @SerializedName("city")
    val cityCode: String?,
    val comment: String?,
    @SerializedName("country")
    val countryCode: String?,
    val formatted: String?,
    val number: String?
)

fun PhonesDto.toDomain(): Phones {
    return Phones(
        city = this.cityCode,
        country = this.countryCode,
        number = this.number,
        comment = this.comment,
        formatted = this.formatted
    )
}