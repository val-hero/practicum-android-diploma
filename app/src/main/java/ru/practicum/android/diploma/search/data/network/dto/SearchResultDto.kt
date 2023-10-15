package ru.practicum.android.diploma.search.data.network.dto

import ru.practicum.android.diploma.search.domain.models.SearchResult

data class SearchResultDto(
    val arguments: Any?,
    val clusters: Any?,
    val fixes: Any?,
    val found: Int,
    val page: Int,
    val pages: Int,
    val per_page: Int,
    val suggests: Any?
)

fun SearchResultDto.toDomain():SearchResult{
    return SearchResult(
        arguments=this.arguments,
        clusters= this.clusters,
        fixes = this.fixes,
        found= this.found,
        page = this.page,
        pages = this.pages,
        per_page = this.per_page,
        suggests = this.suggests,
    )
}
