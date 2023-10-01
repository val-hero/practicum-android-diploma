package ru.practicum.android.diploma.search.data.network.dto.fields

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.search.domain.models.fields.Employer

data class EmployerDto(
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    val id: Long?,
    @SerializedName("logo_urls")
    val logoUrls: EmployerLogoUrlsDto?,
    val name: String,
    val url: String,
) {
    fun toEmployer(): Employer {
        return Employer(
            alternateUrl = this.alternateUrl,
            id = this.id,
            logoUrls = this.logoUrls?.toEmployerLogoUrls(),
            name = this.name,
            url = this.url
        )
    }
}
