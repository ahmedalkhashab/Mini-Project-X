package io.android.projectx.cache.features.restaurants.mapper

import io.android.projectx.cache.base.mapper.CacheMapper
import io.android.projectx.cache.features.restaurants.model.CachedRestaurant
import io.android.projectx.data.features.restaurants.model.RestaurantEntity
import javax.inject.Inject

class CachedRestaurantMapper @Inject constructor() :
    CacheMapper<CachedRestaurant, RestaurantEntity> {

    override fun mapFromCached(type: CachedRestaurant): RestaurantEntity {
        return RestaurantEntity(
            type.id, type.title, type.description,
            type.url, type.urlToImage
        )
    }

    override fun mapToCached(entity: RestaurantEntity): CachedRestaurant {
        return CachedRestaurant(
            entity.id, entity.title, entity.description,
            entity.url, entity.urlToImage
        )
    }

}