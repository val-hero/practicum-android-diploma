package ru.practicum.android.diploma.search.data.network.dto.fields

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.search.domain.models.fields.Employer

data class EmployerDto(
    val id: String,
    @SerializedName("logo_urls")
    val logoUrls: EmployerLogoUrlsDto?,
    val name: String,
    val trusted: Boolean,
    val url: String,
    @SerializedName("vacancies_url")
    val vacanciesUrl: String
)

fun EmployerDto.toDomain(): Employer {
    return Employer(
        id = this.id,
        logoUrls = this.logoUrls?.toDomain(),
        name = this.name,
        url = this.url
    )
}