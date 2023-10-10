package ru.practicum.android.diploma.search.data.network.dto.fields

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.search.domain.models.fields.EmployerLogoUrls

data class EmployerLogoUrlsDto(
    @SerializedName("240")
    val mediumLogo: String?,
    @SerializedName("90")
    val smallLogo: String?,
    val original: String?
)

fun EmployerLogoUrlsDto.toDomain(): EmployerLogoUrls {
    return EmployerLogoUrls(
        smallLogo = this.smallLogo,
        mediumLogo = this.mediumLogo,
        original = this.original
    )
}