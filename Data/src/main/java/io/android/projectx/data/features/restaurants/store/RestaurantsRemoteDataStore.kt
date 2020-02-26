package io.android.projectx.data.features.restaurants.store

import io.android.projectx.data.features.restaurants.model.RestaurantEntity
import io.android.projectx.data.features.restaurants.repository.RestaurantsDataStore
import io.android.projectx.data.features.restaurants.repository.RestaurantsRemote
import io.android.projectx.remote.features.restaurants.mapper.RestaurantsResponseModelMapper
import io.android.projectx.remote.features.restaurants.service.RestaurantsService
import io.reactivex.Flowable
import javax.inject.Inject

open class RestaurantsRemoteDataStore @Inject constructor(
    private val service: RestaurantsService,
    private val mapper: RestaurantsResponseModelMapper
) : RestaurantsRemote {

    override fun getRestaurants(): Flowable<List<RestaurantEntity>> {
        //todo - move parameters
        return service.searchRestaurants(1)
            .map {
                it.items.map { model -> mapper.mapFromModel(model) }
            }
    }

}