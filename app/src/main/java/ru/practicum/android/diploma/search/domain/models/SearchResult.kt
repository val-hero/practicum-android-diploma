package ru.practicum.android.diploma.search.domain.models

data class SearchResult(
    val arguments: Any?,
    val clusters: Any?,
    val fixes: Any?,
    val found: Int,
    val page: Int,
    val pages: Int,
    val per_page: Int,
    val suggests: Any?
)