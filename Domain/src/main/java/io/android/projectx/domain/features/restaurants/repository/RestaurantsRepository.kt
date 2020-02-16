package io.android.projectx.domain.features.restaurants.repository

import io.android.projectx.domain.features.restaurants.model.Restaurant
import io.reactivex.Observable

interface RestaurantsRepository {

    fun getRestaurants(): Observable<List<Restaurant>>
}