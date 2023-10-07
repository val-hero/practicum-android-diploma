package ru.practicum.android.diploma.search.data.network.dto

data class SalaryDto(
    val currency: String,
    val from: Any?,
    val gross: Boolean,
    val to: Int
)