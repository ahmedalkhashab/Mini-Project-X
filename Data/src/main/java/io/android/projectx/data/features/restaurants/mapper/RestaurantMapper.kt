package io.android.projectx.data.features.restaurants.mapper

import io.android.projectx.data.base.mapper.EntityMapper
import io.android.projectx.data.features.restaurants.model.RestaurantEntity
import io.android.projectx.domain.features.restaurants.model.Restaurant
import javax.inject.Inject

open class RestaurantMapper @Inject constructor() :
    EntityMapper<RestaurantEntity, Restaurant> {

    override fun mapFromEntity(entity: RestaurantEntity): Restaurant {
        return Restaurant(
            entity.id, entity.title, entity.description, entity.url,
            entity.urlToImage)
    }

    override fun mapToEntity(domain: Restaurant): RestaurantEntity {
        return RestaurantEntity(
            domain.id, domain.title, domain.description, domain.url,
            domain.urlToImage)
    }

}