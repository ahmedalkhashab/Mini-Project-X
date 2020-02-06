package io.android.projectx.presentation.mapper

import io.android.projectx.domain.model.Recipe
import io.android.projectx.presentation.model.RecipeView
import javax.inject.Inject

open class RecipeViewMapper @Inject constructor() : Mapper<RecipeView, Recipe> {

    override fun mapToView(type: Recipe): RecipeView {
        return RecipeView(
            type.id, type.author, type.title,
            type.description, type.url, type.urlToImage, type.publishedAt,
            type.content, type.isBookmarked
        )
    }
}