package io.android.projectx.presentation.features.browse

interface RecipeListener {

    fun onBookmarkedRecipeClicked(recipeId: Long)

    fun onRecipeClicked(recipeId: Long)

}