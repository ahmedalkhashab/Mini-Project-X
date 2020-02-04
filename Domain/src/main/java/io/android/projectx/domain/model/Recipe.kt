package io.android.projectx.domain.model

import java.util.*

class Recipe(
    val id: Long, val author: String, val title: String, val description: String,
    val url: String, val urlToImage: String, val publishedAt: Date,
    val content: String, val isBookmarked: Boolean
)