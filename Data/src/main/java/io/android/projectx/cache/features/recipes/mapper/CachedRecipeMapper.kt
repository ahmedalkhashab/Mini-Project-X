package io.android.projectx.cache.features.recipes.mapper

import io.android.projectx.cache.base.mapper.CacheMapper
import io.android.projectx.extensions.getDate
import io.android.projectx.extensions.getOffsetDate
import io.android.projectx.cache.features.recipes.model.CachedRecipe
import io.android.projectx.data.features.recipes.model.RecipeEntity
import java.util.*
import javax.inject.Inject

class CachedRecipeMapper @Inject constructor() :
    CacheMapper<CachedRecipe, RecipeEntity> {

    override fun mapFromCached(type: CachedRecipe): RecipeEntity {
        return RecipeEntity(
            type.id, type.author, type.title, type.description,
            type.url, type.urlToImage, type.publishedAt.getDate() ?: Date(), type.content,
            type.isBookmarked
        )
    }

    override fun mapToCached(entity: RecipeEntity): CachedRecipe {
        return CachedRecipe(
            entity.id, entity.author, entity.title, entity.description,
            entity.url, entity.urlToImage, entity.publishedAt.getOffsetDate(), entity.content,
            entity.isBookmarked
        )
    }

}