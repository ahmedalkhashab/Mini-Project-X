package io.android.projectx.presentation.base.mapper

import io.android.projectx.domain.features.recipes.model.Recipe
import io.android.projectx.presentation.base.model.RecipeView
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