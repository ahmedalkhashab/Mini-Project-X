package io.android.projectx.remote.mapper

import io.android.projectx.data.model.RecipeEntity
import io.android.projectx.remote.model.RecipeModel

open class RecipesResponseModelMapper :
    ModelMapper<RecipeModel, RecipeEntity> {

    override fun mapFromModel(model: RecipeModel): RecipeEntity {
        return RecipeEntity(
            model.id, model.author.authorName, model.title, model.description, model.url,
            model.urlToImage, model.publishedAt, model.content, false
        )
    }

}