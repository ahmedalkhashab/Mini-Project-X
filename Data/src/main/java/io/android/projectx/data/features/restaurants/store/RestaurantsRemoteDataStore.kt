package io.android.projectx.data.features.restaurants.store

import io.android.projectx.data.features.restaurants.model.RestaurantEntity
import io.android.projectx.data.features.restaurants.repository.RestaurantsDataStore
import io.android.projectx.data.features.restaurants.repository.RestaurantsRemote
import io.reactivex.Flowable
import javax.inject.Inject

open class RestaurantsRemoteDataStore @Inject constructor(
    private val restaurantsRemote: RestaurantsRemote
) : RestaurantsDataStore {

    override fun getRestaurants(): Flowable<List<RestaurantEntity>> {
        return restaurantsRemote.getRestaurants()
    }

}