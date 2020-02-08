package io.android.projectx.remote

import io.android.projectx.data.model.RecipeEntity
import io.android.projectx.data.repository.RecipesRemote
import io.android.projectx.remote.mapper.RecipesResponseModelMapper
import io.android.projectx.remote.service.RecipesService
import io.reactivex.Flowable
import javax.inject.Inject

class RecipesRemoteImpl @Inject constructor(
    private val service: RecipesService,
    private val mapper: RecipesResponseModelMapper
) : RecipesRemote {

    override fun getRecipes(): Flowable<List<RecipeEntity>> {
        //todo - move parameters
        return service.searchRepositories(1)
            .map {
                it.items.map { model -> mapper.mapFromModel(model) }
            }
    }

}