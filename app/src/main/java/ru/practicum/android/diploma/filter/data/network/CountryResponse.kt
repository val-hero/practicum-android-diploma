package ru.practicum.android.diploma.filter.data.network

import ru.practicum.android.diploma.filter.data.network.dto.fields.CountryDto
import ru.practicum.android.diploma.search.data.network.api.ApiResponse

class CountryResponse (val result : List<CountryDto>): ApiResponse()