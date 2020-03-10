package io.android.projectx.cache.features.config.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.android.projectx.cache.features.config.db.ConfigConstants.DELETE_CONFIG
import io.android.projectx.cache.features.config.db.ConfigConstants.DELETE_CONFIG_ITEM
import io.android.projectx.cache.features.config.db.ConfigConstants.QUERY_ALL_CONFIG
import io.android.projectx.cache.features.config.db.ConfigConstants.QUERY_CONFIG_ITEM
import io.android.projectx.cache.features.config.model.Config
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class ConfigDao {

    @Query(QUERY_CONFIG_ITEM)
    abstract fun getConfig(key: String): Single<Config>

    @Query(QUERY_ALL_CONFIG)
    @JvmSuppressWildcards
    abstract fun getAllConfig(): Flowable<List<Config>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertConfig(config: Config)

    @Query(DELETE_CONFIG_ITEM)
    abstract fun deleteConfigItem(key: String)

    @Query(DELETE_CONFIG)
    abstract fun deleteAllConfig()

}