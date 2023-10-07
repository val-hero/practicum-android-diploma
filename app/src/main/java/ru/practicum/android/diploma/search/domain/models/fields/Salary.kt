package ru.practicum.android.diploma.search.domain.models.fields

data class Salary(
    val currency: String?,
    val from: Long?,
    val gross: Boolean?,
    val to: String?
) {
//    fun toDto(): SalaryDto {
//        return SalaryDto(
//            currency = this.currency,
//            from = this.from,
//            gross = this.gross,
//            to = this.to
//        )
//    }
}
