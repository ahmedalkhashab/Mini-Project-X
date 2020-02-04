package io.android.projectx.data.cache.mapper

import io.android.projectx.data.cache.model.CachedRecipe
import io.android.projectx.data.model.RecipeEntity
import java.util.*
import javax.inject.Inject

class CachedRecipeMapper @Inject constructor() : CacheMapper<CachedRecipe, RecipeEntity> {

    override fun mapFromCached(type: CachedRecipe): RecipeEntity {
        return RecipeEntity(
            type.id, type.author, type.title, type.description,
            type.url, type.urlToImage, type.publishedAt.getDate() ?: Date(), type.content,
            type.isBookmarked
        )
    }

    override fun mapToCached(type: RecipeEntity): CachedRecipe {
        return CachedRecipe(
            type.id, type.author, type.title, type.description,
            type.url, type.urlToImage, type.publishedAt.getOffsetDate(), type.content,
            type.isBookmarked
        )
    }

}