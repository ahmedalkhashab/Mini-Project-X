package io.android.projectx.presentation.test.factory

import io.android.projectx.domain.model.Recipe
import io.android.projectx.presentation.model.RecipeView

object RecipeDataFactory {

    fun makeRecipeView(): RecipeView {
        return RecipeView(
            TestDataFactory.uniqueId(), TestDataFactory.randomString(),
            TestDataFactory.randomString(), TestDataFactory.randomString(),
            TestDataFactory.randomString(), TestDataFactory.randomString(),
            TestDataFactory.randomDate(), TestDataFactory.randomString(),
            TestDataFactory.randomBoolean()
        )
    }

    fun makeRecipe(): Recipe {
        return Recipe(
            TestDataFactory.uniqueId(), TestDataFactory.randomString(),
            TestDataFactory.randomString(), TestDataFactory.randomString(),
            TestDataFactory.randomString(), TestDataFactory.randomString(),
            TestDataFactory.randomDate(), TestDataFactory.randomString(),
            TestDataFactory.randomBoolean()
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