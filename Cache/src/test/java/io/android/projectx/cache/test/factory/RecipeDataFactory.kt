package io.android.projectx.cache.test.factory

import io.android.projectx.cache.recipes.mapper.getOffsetDate
import io.android.projectx.cache.recipes.model.CachedRecipe
import io.android.projectx.data.model.RecipeEntity

object RecipeDataFactory {

    fun makeCachedRecipe(): CachedRecipe {
        return CachedRecipe(
            DataFactory.uniqueId(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomDate().getOffsetDate(), DataFactory.randomString(),
            false
        )
    }

    fun makeBookmarkedCachedRecipe(): CachedRecipe {
        return CachedRecipe(
            DataFactory.uniqueId(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomDate().getOffsetDate(), DataFactory.randomString(),
            true
        )
    }

    fun makeRecipeEntity(): RecipeEntity {
        return RecipeEntity(
            DataFactory.uniqueId(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomDate(), DataFactory.randomString(),
            DataFactory.randomBoolean()
        )
    }

    fun makeBookmarkedRecipeEntity(): RecipeEntity {
        return RecipeEntity(
            DataFactory.uniqueId(),
            DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomDate(), DataFactory.randomString(),
            true
        )
    }

}