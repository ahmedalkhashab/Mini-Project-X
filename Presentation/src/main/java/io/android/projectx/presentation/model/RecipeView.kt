package io.android.projectx.presentation.model

import java.util.*

class RecipeView(
    val id: Long, val author: String, val title: String, val description: String,
    val url: String, val urlToImage: String, val publishedAt: Date,
    val content: String, val isBookmarked: Boolean
)