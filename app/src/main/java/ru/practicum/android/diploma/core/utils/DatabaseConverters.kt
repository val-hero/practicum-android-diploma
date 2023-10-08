package ru.practicum.android.diploma.core.utils

import androidx.room.TypeConverter
import ru.practicum.android.diploma.search.domain.models.fields.Area
import ru.practicum.android.diploma.search.domain.models.fields.Contacts
import ru.practicum.android.diploma.search.domain.models.fields.Employer
import ru.practicum.android.diploma.search.domain.models.fields.EmployerLogoUrls
import ru.practicum.android.diploma.search.domain.models.fields.Employment
import ru.practicum.android.diploma.search.domain.models.fields.Experience
import ru.practicum.android.diploma.search.domain.models.fields.KeySkills
import ru.practicum.android.diploma.search.domain.models.fields.Phones
import ru.practicum.android.diploma.search.domain.models.fields.Salary
import ru.practicum.android.diploma.search.domain.models.fields.Schedule

class DatabaseConverters {
    @TypeConverter
    fun fromArea(value: Area) = JsonConverter.itemToJson(value)

    @TypeConverter
    fun toArea(value: String) = JsonConverter.jsonToItem<Area>(value)

    @TypeConverter
    fun fromContacts(value: Contacts) = JsonConverter.itemToJson(value)

    @TypeConverter
    fun toContacts(value: String) = JsonConverter.jsonToItem<Contacts>(value)

    @TypeConverter
    fun fromPhones(value: Phones) = JsonConverter.itemToJson(value)

    @TypeConverter
    fun toPhones(value: String) = JsonConverter.jsonToItem<Phones>(value)

    @TypeConverter
    fun fromPhonesList(value: List<Phones>) = JsonConverter.itemListToJson(value)

    @TypeConverter
    fun toPhonesList(value: String) = JsonConverter.jsonToItemList<Phones>(value)

    @TypeConverter
    fun fromEmployer(value: Employer) = JsonConverter.itemToJson(value)

    @TypeConverter
    fun toEmployer(value: String) = JsonConverter.jsonToItem<Employer>(value)

    @TypeConverter
    fun fromEmployerLogoUrls(value: EmployerLogoUrls) = JsonConverter.itemToJson(value)

    @TypeConverter
    fun toEmployerLogoUrls(value: String) = JsonConverter.jsonToItem<EmployerLogoUrls>(value)

    @TypeConverter
    fun fromEmployment(value: Employment) = JsonConverter.itemToJson(value)

    @TypeConverter
    fun toEmployment(value: String) = JsonConverter.jsonToItem<Employment>(value)

    @TypeConverter
    fun fromExperience(value: Experience) = JsonConverter.itemToJson(value)

    @TypeConverter
    fun toExperience(value: String) = JsonConverter.jsonToItem<Experience>(value)

    @TypeConverter
    fun fromKeySkills(value: KeySkills) = JsonConverter.itemToJson(value)

    @TypeConverter
    fun toKeySkills(value: String) = JsonConverter.jsonToItem<KeySkills>(value)

    @TypeConverter
    fun fromKeySkillsList(value: List<KeySkills>) = JsonConverter.itemListToJson(value)

    @TypeConverter
    fun toKeySkillsList(value: String) = JsonConverter.jsonToItemList<KeySkills>(value)

    @TypeConverter
    fun fromSalary(value: Salary) = JsonConverter.itemToJson(value)

    @TypeConverter
    fun toSalary(value: String) = JsonConverter.jsonToItem<Salary>(value)

    @TypeConverter
    fun fromSchedule(value: Schedule) = JsonConverter.itemToJson(value)

    @TypeConverter
    fun toSchedule(value: String) = JsonConverter.jsonToItem<Schedule>(value)
}