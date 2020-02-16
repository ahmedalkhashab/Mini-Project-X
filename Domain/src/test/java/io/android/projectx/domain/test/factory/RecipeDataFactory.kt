package io.android.projectx.domain.test.factory

import io.android.projectx.domain.features.recipes.model.Recipe

object RecipeDataFactory {

    fun makeRecipe(): Recipe {
        return Recipe(
            DataFactory.uniqueId(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomDate(),
            DataFactory.randomString(),
            DataFactory.randomBoolean()
        )
    }

    fun makeRecipesList(count:Int):List<Recipe>{
        val recipes = mutableListOf<Recipe>()
        repeat(count){recipes.add(makeRecipe())}
        return recipes
    }

}