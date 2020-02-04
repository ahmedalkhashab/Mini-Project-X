package io.android.projectx.data.test.factory

import io.android.projectx.data.model.RecipeEntity
import io.android.projectx.domain.model.Recipe

object RecipeFactory {

    fun makeRecipeEntity(): RecipeEntity {
        return RecipeEntity(
            DataFactory.uniqueId(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDate(),
            DataFactory.randomString(), DataFactory.randomBoolean()
        )
    }

    fun makeRecipe(): Recipe {
        return Recipe(
            DataFactory.uniqueId(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDate(),
            DataFactory.randomString(), DataFactory.randomBoolean()
        )
    }

}