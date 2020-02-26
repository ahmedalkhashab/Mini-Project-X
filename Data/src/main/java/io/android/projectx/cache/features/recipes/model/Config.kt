package io.android.projectx.cache.features.recipes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.android.projectx.cache.features.recipes.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(
    @PrimaryKey
    var id: Int = 17,
    var lastCacheTime: Long)
