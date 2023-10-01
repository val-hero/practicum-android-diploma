package ru.practicum.android.diploma.search.data.network.dto.fields

import ru.practicum.android.diploma.search.domain.models.fields.Salary

data class SalaryDto(
    val currency: String?,
    val from: Long?,
    val gross: Boolean?,
    val to: String?
) {
    fun toSalary(): Salary {
        return Salary(
            currency = this.currency,
            from = this.from,
            gross = this.gross,
            to = this.to
        )
    }
}
