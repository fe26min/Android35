package com.fe26min.part2.part2.chapter05

data class NewsModel (
    val title: String,
    val link: String,
    var imageUrl: String? = null

)

fun List<NewsItem>.transform() : List<NewsModel> {
    return this.map {
        NewsModel(
            title = it.title ?: "",
            link = it.link ?: "",
            imageUrl = null
        )
    }
}