package io.android.projectx.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.android.projectx.cache.features.recipes.dao.CachedRecipesDao
import io.android.projectx.cache.features.recipes.dao.ConfigDao
import io.android.projectx.cache.features.recipes.model.CachedRecipe
import io.android.projectx.cache.features.recipes.model.Config
import io.android.projectx.cache.features.restaurants.dao.CachedRestaurantDao
import io.android.projectx.cache.features.restaurants.model.CachedRestaurant
import javax.inject.Inject

@Database(
    entities = [CachedRecipe::class,
        Config::class,
        CachedRestaurant::class],
    version = 1
)
abstract class AppDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedRecipesDao(): CachedRecipesDao
    abstract fun configDao(): ConfigDao
    abstract fun cachedRestaurantDao(): CachedRestaurantDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "app.db"
                        )
                            .build()
                    }
                    return INSTANCE as AppDatabase
                }
            }
            return INSTANCE as AppDatabase
        }
    }

}