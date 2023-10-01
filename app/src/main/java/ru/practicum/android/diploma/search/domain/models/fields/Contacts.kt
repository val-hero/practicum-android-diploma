package ru.practicum.android.diploma.search.domain.models.fields

import ru.practicum.android.diploma.search.data.network.dto.fields.ContactsDto

data class Contacts(
    val email: String?,
    val name: String?,
    val phones: Phones?
) {
    fun toDto(): ContactsDto {
        return ContactsDto(
            email = this.email,
            name = this.name,
            phonesDto = this.phones?.toDto()
        )
    }
}
