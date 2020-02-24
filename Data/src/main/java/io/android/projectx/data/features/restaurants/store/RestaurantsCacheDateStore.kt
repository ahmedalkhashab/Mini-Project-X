package io.android.projectx.data.features.restaurants.store

import io.android.projectx.data.features.restaurants.model.RestaurantEntity
import io.android.projectx.data.features.restaurants.repository.RestaurantsCache
import io.android.projectx.data.features.restaurants.repository.RestaurantsDataStore
import io.reactivex.Flowable
import javax.inject.Inject

open class RestaurantsCacheDateStore @Inject constructor(
    private val restaurantsCache: RestaurantsCache
) : RestaurantsCache {

    override fun getRestaurants(): Flowable<List<RestaurantEntity>> {
        throw UnsupportedOperationException("Getting Restaurants isn't supported here...")
    }

}