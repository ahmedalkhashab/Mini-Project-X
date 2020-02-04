package io.android.projectx.data.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.android.projectx.data.cache.model.CachedRecipe
import javax.inject.Inject

@Database(entities = arrayOf(CachedRecipe::class), version = 1)
abstract class RecipesDatabase @Inject constructor(): RoomDatabase() {

    companion object {

        private var INSTANCE: RecipesDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): RecipesDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            RecipesDatabase::class.java, "recipes.db")
                            .build()
                    }
                    return INSTANCE as RecipesDatabase
                }
            }
            return INSTANCE as RecipesDatabase
        }
    }

}