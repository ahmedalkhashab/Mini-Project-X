package io.android.projectx.cache.features.config.db

object ConfigConstants {

    const val TABLE_NAME = "config"
    private const val COLUMN_KEY = "key"
    private const val COLUMN_VALUE = "value"

    const val COLUMN_LAST_CACHE_TIME = "last_cache_time"

    const val QUERY_CONFIG_ITEM = "SELECT * FROM $TABLE_NAME WHERE " +
            "$COLUMN_KEY LIKE :key LIMIT 1"

    const val QUERY_ALL_CONFIG = "SELECT * FROM $TABLE_NAME"

    const val DELETE_CONFIG_ITEM = "DELETE FROM $TABLE_NAME WHERE " +
            "$COLUMN_KEY LIKE :key"

    const val DELETE_CONFIG = "DELETE FROM $TABLE_NAME"
}