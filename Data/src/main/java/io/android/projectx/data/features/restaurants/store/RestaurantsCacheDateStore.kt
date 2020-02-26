package io.android.projectx.data.features.restaurants.store

import io.android.projectx.cache.AppDatabase
import io.android.projectx.cache.features.restaurants.mapper.CachedRestaurantMapper
import io.android.projectx.data.features.restaurants.model.RestaurantEntity
import io.android.projectx.data.features.restaurants.repository.RestaurantsCache
import io.android.projectx.data.features.restaurants.repository.RestaurantsDataStore
import io.reactivex.Flowable
import javax.inject.Inject

open class RestaurantsCacheDateStore @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: CachedRestaurantMapper
) : RestaurantsCache {

    override fun getRestaurants(): Flowable<List<RestaurantEntity>> {
        return appDatabase.cachedRestaurantDao().getRestaurants()
            .map {
                it.map { cachedRestaurant -> mapper.mapFromCached(cachedRestaurant) }
            }
    }

}