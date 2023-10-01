package ru.practicum.android.diploma.search.domain.models.fields

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.search.data.network.dto.fields.EmployerDto

data class Employer(
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    val id: Long?,
    @SerializedName("logo_urls")
    val logoUrls: EmployerLogoUrls?,
    val name: String,
    val url: String,
) {
    fun toDto(): EmployerDto {
        return EmployerDto(
            alternateUrl = this.alternateUrl,
            id = this.id,
            logoUrls = this.logoUrls?.toDto(),
            name = this.name,
            url = this.url
        )
    }
}
