package ru.practicum.android.diploma.search.data.network.dto.fields

import ru.practicum.android.diploma.search.domain.models.fields.Contacts

data class ContactsDto(
    val email: String?,
    val name: String?,
    val phonesDto: PhonesDto?
) {
    fun toContacts(): Contacts {
        return Contacts(
            email = this.email,
            name = this.name,
            phones = this.phonesDto?.toPhones()
        )
    }
}
