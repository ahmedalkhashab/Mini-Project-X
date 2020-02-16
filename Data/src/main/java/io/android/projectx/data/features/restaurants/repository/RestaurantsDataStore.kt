package io.android.projectx.data.features.restaurants.repository

import io.android.projectx.data.features.restaurants.model.RestaurantEntity
import io.reactivex.Flowable

interface RestaurantsDataStore {

    fun getRestaurants(): Flowable<List<RestaurantEntity>>
}