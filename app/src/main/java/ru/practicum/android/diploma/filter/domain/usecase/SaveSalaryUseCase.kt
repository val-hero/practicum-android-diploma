package ru.practicum.android.diploma.filter.domain.usecase

import ru.practicum.android.diploma.filter.domain.api.FilterStorage

class SaveSalaryUseCase(private val filterStorage: FilterStorage) {
    suspend operator fun invoke(salary: Int) {
        return filterStorage.saveSalary(salary)
    }
}