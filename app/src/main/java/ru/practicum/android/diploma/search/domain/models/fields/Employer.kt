package ru.practicum.android.diploma.search.domain.models.fields

data class Employer(
    val alternateUrl: String,
    val id: String,
    val logoUrls: EmployerLogoUrls?,
    val name: String,
    val url: String,
) {
//    fun toDto(): EmployerDto {
//        return EmployerDto(
//            alternate_url = this.alternateUrl,
//            id = this.id,
//            logo_urls = this.logoUrls?.toDto(),
//            name = this.name,
//            url = this.url,
//            trusted = true,
//            accredited_it_employer = true,
//            vacancies_url = ""
//        )
//    }
}
