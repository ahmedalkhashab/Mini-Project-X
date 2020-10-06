package io.android.projectx.data.features.recipes.model

import java.util.*

class RecipeEntity(
    val id: Long, val title: String, val description: String,
    val url: String, val urlToImage: String, val publishedAt: Date,
    val isBookmarked: Boolean
)