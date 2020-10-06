package io.android.projectx.presentation.base.model

import java.util.*

class RecipeView(
    val id: Long, val title: String, val description: String,
    val url: String, val urlToImage: String, val publishedAt: Date,
    val isBookmarked: Boolean
)