package ru.practicum.android.diploma.search.domain.models

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.search.domain.models.fields.Address
import ru.practicum.android.diploma.search.domain.models.fields.Contacts
import ru.practicum.android.diploma.search.domain.models.fields.Department
import ru.practicum.android.diploma.search.domain.models.fields.DriverLicenseType
import ru.practicum.android.diploma.search.domain.models.fields.Employer
import ru.practicum.android.diploma.search.domain.models.fields.Employment
import ru.practicum.android.diploma.search.domain.models.fields.Experience
import ru.practicum.android.diploma.search.domain.models.fields.KeySkill
import ru.practicum.android.diploma.search.domain.models.fields.Languages
import ru.practicum.android.diploma.search.domain.models.fields.ProfessionalRoles
import ru.practicum.android.diploma.search.domain.models.fields.Salary
import ru.practicum.android.diploma.search.domain.models.fields.Schedule
import ru.practicum.android.diploma.search.domain.models.fields.Type
import ru.practicum.android.diploma.search.domain.models.fields.WorkingDays
import ru.practicum.android.diploma.search.domain.models.fields.WorkingTimeIntervals
import ru.practicum.android.diploma.search.domain.models.fields.WorkingTimeModels

data class Vacancy(
    val address: Address?,
    val contacts: Contacts?,
    val department: Department,
    @SerializedName("driver_license_types")
    val driverLicense: List<DriverLicenseType>?,
    val employer: Employer?,
    val employment: Employment?,
    val experience: Experience?,
    val id: Long?,
    @SerializedName("key_skills")
    val keySkills: List<KeySkill>?,
    val languages: Languages?,
    val name: String,
    @SerializedName("professional_roles")
    val professionalRoles: ProfessionalRoles?,
    val salary: Salary?,
    val schedule: Schedule?,
    val type: Type?,
    @SerializedName("working_days")
    val workingDays: WorkingDays?,
    @SerializedName("working_time_intervals")
    val workingTimeIntervals: WorkingTimeIntervals?,
    @SerializedName("working_time_modes")
    val workingTimeModels: WorkingTimeModels?,
)
