package io.android.projectx.data.features.restaurants.store

import io.android.projectx.data.features.restaurants.repository.RestaurantsCache
import io.android.projectx.data.features.restaurants.repository.RestaurantsDataStore
import io.android.projectx.data.features.restaurants.repository.RestaurantsRemote
import javax.inject.Inject

open class RestaurantsDataStoreFactory @Inject constructor(
    private val restaurantsCacheDateStore: RestaurantsCacheDateStore,
    private val restaurantsRemoteDataStore: RestaurantsRemoteDataStore
) {

    open fun getDataStore(): RestaurantsDataStore {
        return restaurantsRemoteDataStore
    }

    open fun getCacheDateStore(): RestaurantsCache {
        return restaurantsCacheDateStore
    }

    open fun getRemoteDataStore(): RestaurantsRemote {
        return restaurantsRemoteDataStore
    }

}