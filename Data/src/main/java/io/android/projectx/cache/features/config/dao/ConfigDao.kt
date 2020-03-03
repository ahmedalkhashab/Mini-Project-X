package io.android.projectx.cache.features.config.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.android.projectx.cache.features.config.db.ConfigConstants
import io.android.projectx.cache.features.config.model.Config
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class ConfigDao {

    @Query(ConfigConstants.QUERY_CONFIG_ITEM)
    abstract fun getConfig(key: String): Single<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config): Completable

    @Query(ConfigConstants.DELETE_CONFIG_ITEM)
    abstract fun deleteConfigItem(key: String): Completable

    @Query(ConfigConstants.DELETE_CONFIG)
    abstract fun deleteAllConfig(): Completable

}