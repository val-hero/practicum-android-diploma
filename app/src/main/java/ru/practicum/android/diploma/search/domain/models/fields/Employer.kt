package ru.practicum.android.diploma.search.domain.models.fields

import com.google.gson.annotations.SerializedName

data class Employer(
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    val id: Long?,
    @SerializedName("logo_urls")
    val logoUrls: EmployerLogoUrls?,
    val name: String,
    val url: String,
)
