package io.android.projectx.data.features.recipes.store

import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.data.features.recipes.repository.RecipesRemote
import io.android.projectx.remote.features.recipes.mapper.RecipesResponseModelMapper
import io.android.projectx.remote.features.recipes.service.RecipesService
import io.reactivex.Flowable
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Named

open class RecipesRemoteDataStore @Inject constructor(
    private val service: RecipesService,
    private val mapper: RecipesResponseModelMapper,
    @Named("api.key.recipes") private val apiKey: String
) : RecipesRemote {

    override fun getRecipes(fromIndex: Int, pageSize: Int): Flowable<List<RecipeEntity>> {
        val params = HashMap<String, String>()
        params["from"] = fromIndex.toString()
        params["sizes"] = pageSize.toString()
        params["tags"] = "under_30_minutes"
        return service.searchRecipes("tasty.p.rapidapi.com", apiKey, params)
            .map {
                it.items?.map { model -> mapper.mapFromModel(model) }
            }
    }

}