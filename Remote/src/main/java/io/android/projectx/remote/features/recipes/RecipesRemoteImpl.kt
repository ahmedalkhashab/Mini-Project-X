package io.android.projectx.remote.features.recipes

import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.data.features.recipes.repository.RecipesRemote
import io.android.projectx.remote.features.recipes.mapper.RecipesResponseModelMapper
import io.android.projectx.remote.features.recipes.service.RecipesService
import io.reactivex.Flowable
import javax.inject.Inject

class RecipesRemoteImpl @Inject constructor(
    private val service: RecipesService,
    private val mapper: RecipesResponseModelMapper
) : RecipesRemote {

    override fun getRecipes(): Flowable<List<RecipeEntity>> {
        //todo - move parameters
        return service.searchRecipes(1)
            .map {
                it.items.map { model -> mapper.mapFromModel(model) }
            }
    }

}