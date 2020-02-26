package io.android.projectx.remote.features.restaurants.service

import io.android.projectx.remote.features.restaurants.model.response.RestaurantsResponseModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantsService {

    @GET("search/{page}")
    fun searchRestaurants(@Path("page") pageNumber: Int): Flowable<RestaurantsResponseModel>
}