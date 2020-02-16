package io.android.projectx.remote.features.recipes.service

import io.android.projectx.remote.features.recipes.model.response.RecipesResponseModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipesService {

    @GET("search/{page}")
    fun searchRecipes(@Path("page") pageNumber: Int): Flowable<RecipesResponseModel>
}