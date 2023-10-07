package ru.practicum.android.diploma.search.data.network.client

import ru.practicum.android.diploma.search.data.network.api.ApiRequest
import ru.practicum.android.diploma.search.data.network.api.ApiResponse

interface NetworkClient {
    suspend fun doRequest(request: ApiRequest): ApiResponse
}