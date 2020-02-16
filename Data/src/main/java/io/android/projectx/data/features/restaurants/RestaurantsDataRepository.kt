package io.android.projectx.data.features.restaurants

import io.android.projectx.data.features.restaurants.mapper.RestaurantMapper
import io.android.projectx.data.features.restaurants.repository.RestaurantsCache
import io.android.projectx.data.features.restaurants.store.RestaurantsDataStoreFactory
import io.android.projectx.domain.features.restaurants.model.Restaurant
import io.android.projectx.domain.features.restaurants.repository.RestaurantsRepository
import io.reactivex.Observable
import javax.inject.Inject

class RestaurantsDataRepository @Inject constructor(
    private val mapper: RestaurantMapper,
    private val cache: RestaurantsCache,
    private val factory: RestaurantsDataStoreFactory
) : RestaurantsRepository {

    override fun getRestaurants(): Observable<List<Restaurant>> {
        return factory.getDataStore().getRestaurants().toObservable()
            .map {
                it.map { entity -> mapper.mapFromEntity(entity) }
            }
    }

}