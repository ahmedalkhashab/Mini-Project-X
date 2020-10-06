package io.android.projectx.domain.features.recipes.model

import java.util.*

class Recipe(
    val id: Long, val title: String, val description: String,
    val url: String, val urlToImage: String, val publishedAt: Date,
    val isBookmarked: Boolean
)