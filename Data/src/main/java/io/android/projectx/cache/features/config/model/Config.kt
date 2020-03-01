package io.android.projectx.cache.features.config.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.android.projectx.cache.features.config.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(
    @PrimaryKey
    var key: String,
    var value: String,
    @ColumnInfo(name = ConfigConstants.COLUMN_LAST_CACHE_TIME)
    var lastCacheTime: Long
)