package io.android.projectx.cache.features.config.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.android.projectx.cache.features.config.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(
    @PrimaryKey
    val key: String,
    val value: String,
    @ColumnInfo(name = ConfigConstants.COLUMN_LAST_CACHE_TIME)
    val lastCacheTime: Long
)