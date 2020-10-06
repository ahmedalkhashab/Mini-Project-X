package io.android.projectx.remote.features.recipes.service

import io.android.projectx.remote.features.recipes.model.response.RecipesResponseModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface RecipesService {

    @GET("recipes/list")
    fun searchRecipes(
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") key: String,
        @QueryMap params: Map<String, String>
    ): Flowable<RecipesResponseModel>
}