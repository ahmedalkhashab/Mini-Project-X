package io.android.projectx.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.android.projectx.cache.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    var lastCacheTime: Long)
