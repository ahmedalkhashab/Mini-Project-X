package io.android.projectx.data.test.factory

import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.data.test.factory.DataFactory
import io.android.projectx.domain.features.recipes.model.Recipe

object RecipeFactory {

    fun makeRecipeEntity(): RecipeEntity {
        return RecipeEntity(
            DataFactory.uniqueId(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomDate(),
            DataFactory.randomBoolean()
        )
    }

    fun makeRecipe(): Recipe {
        return Recipe(
            DataFactory.uniqueId(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomDate(),
            DataFactory.randomBoolean()
        )
    }

}