package io.android.projectx.remote.features.recipes.mapper

import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.remote.base.mapper.ModelMapper
import io.android.projectx.remote.features.recipes.model.RecipeModel
import javax.inject.Inject

open class RecipesResponseModelMapper @Inject constructor() :
    ModelMapper<RecipeModel, RecipeEntity> {

    override fun mapFromModel(model: RecipeModel): RecipeEntity {
        return RecipeEntity(
            model.id, model.author.authorName, model.title, model.description, model.url,
            model.urlToImage, model.publishedAt, model.content, false
        )
    }

}