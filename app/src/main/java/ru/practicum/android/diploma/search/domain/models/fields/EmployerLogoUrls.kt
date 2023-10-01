package ru.practicum.android.diploma.search.domain.models.fields

import com.google.gson.annotations.SerializedName

data class EmployerLogoUrls(
    @SerializedName("90")
    val smallLogo: String?,
    @SerializedName("240")
    val middleLogo: String?,
    val original: String?
)
