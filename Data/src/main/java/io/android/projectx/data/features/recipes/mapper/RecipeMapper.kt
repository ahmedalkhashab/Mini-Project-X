package io.android.projectx.data.features.recipes.mapper

import io.android.projectx.data.base.mapper.EntityMapper
import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.domain.features.recipes.model.Recipe
import javax.inject.Inject

open class RecipeMapper @Inject constructor() :
    EntityMapper<RecipeEntity, Recipe> {

    override fun mapFromEntity(entity: RecipeEntity): Recipe {
        return Recipe(
            entity.id, entity.author, entity.title, entity.description, entity.url,
            entity.urlToImage, entity.publishedAt, entity.content, entity.isBookmarked
        )
    }

    override fun mapToEntity(domain: Recipe): RecipeEntity {
        return RecipeEntity(
            domain.id, domain.author, domain.title, domain.description, domain.url,
            domain.urlToImage, domain.publishedAt, domain.content, domain.isBookmarked
        )
    }

}