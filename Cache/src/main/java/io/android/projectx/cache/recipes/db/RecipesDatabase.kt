package io.android.projectx.cache.recipes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.android.projectx.cache.recipes.dao.CachedRecipesDao
import io.android.projectx.cache.recipes.dao.ConfigDao
import io.android.projectx.cache.recipes.model.CachedRecipe
import io.android.projectx.cache.recipes.model.Config
import javax.inject.Inject

@Database(entities = arrayOf(CachedRecipe::class, Config::class), version = 1)
abstract class RecipesDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedRecipesDao(): CachedRecipesDao

    abstract fun configDao(): ConfigDao

    companion object {

        private var INSTANCE: RecipesDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): RecipesDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RecipesDatabase::class.java, "recipes.db"
                        )
                            .build()
                    }
                    return INSTANCE as RecipesDatabase
                }
            }
            return INSTANCE as RecipesDatabase
        }
    }

}