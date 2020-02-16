package io.android.projectx.cache.features.restaurants.db

object RestaurantConstants {

    const val TABLE_NAME = "restaurants"

    const val COLUMN_RESTAURANT_ID = "restaurant_id"

    const val QUERY_RESTAURANTS = "SELECT * FROM $TABLE_NAME"

    const val DELETE_RESTAURANTS = "DELETE FROM $TABLE_NAME"

}