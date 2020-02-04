package io.android.projectx.remote.service

import io.android.projectx.remote.model.RecipesResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesService {

    @GET("search/recipes")
    fun searchRepositories(
        @Query("query") query: String,
        @Query("page") pageNumber: Int,
        @Query("sort") sortBy: String,
        @Query("order") order: String
    ): Observable<RecipesResponseModel>
}