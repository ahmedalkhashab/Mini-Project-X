package io.android.projectx.presentation.test.factory

import io.android.projectx.domain.features.recipes.model.Recipe
import io.android.projectx.presentation.base.model.RecipeView

object RecipeFactory {

    fun makeRecipeView(): RecipeView {
        return RecipeView(
            DataFactory.uniqueId(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomDate(),
            DataFactory.randomBoolean()
        )
    }

    fun makeRecipe(): Recipe {
        return Recipe(
            DataFactory.uniqueId(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomDate(),
            DataFactory.randomBoolean()
        )
    }

    fun makeRecipeViewList(count: Int): List<RecipeView> {
        val recipes = mutableListOf<RecipeView>()
        repeat(count) {
            recipes.add(makeRecipeView())
        }
        return recipes
    }

    fun makeRecipeList(count: Int): List<Recipe> {
        val recipes = mutableListOf<Recipe>()
        repeat(count) {
            recipes.add(makeRecipe())
        }
        return recipes
    }

}