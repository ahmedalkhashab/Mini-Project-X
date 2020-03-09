package io.android.projectx.presentation.base.mapper

import io.android.projectx.domain.features.restaurants.model.Restaurant
import io.android.projectx.presentation.base.model.RestaurantView
import javax.inject.Inject

open class RestaurantViewMapper @Inject constructor() : Mapper<RestaurantView, Restaurant> {

    override fun mapToView(type: Restaurant): RestaurantView {
        return RestaurantView(type.id, type.title, type.description, type.url, type.urlToImage)
    }
}