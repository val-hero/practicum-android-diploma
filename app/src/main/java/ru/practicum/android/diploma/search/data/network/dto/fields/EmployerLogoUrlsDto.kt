package ru.practicum.android.diploma.search.data.network.dto.fields

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.search.domain.models.fields.EmployerLogoUrls

data class EmployerLogoUrlsDto(
    @SerializedName("90")
    val smallLogo: String?,
    @SerializedName("240")
    val middleLogo: String?,
    val original: String?
) {
    fun toEmployerLogoUrls(): EmployerLogoUrls {
        return EmployerLogoUrls(
            smallLogo = this.smallLogo,
            middleLogo = this.middleLogo,
            original = this.original
        )
    }
}
