package io.android.projectx.data.features.recipes.store

import io.android.projectx.cache.AppDatabase
import io.android.projectx.cache.features.recipes.mapper.CachedRecipeMapper
import io.android.projectx.cache.features.config.model.Config
import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.data.features.recipes.repository.RecipesCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

open class RecipesCacheDateStore @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: CachedRecipeMapper
) : RecipesCache {

    companion object{
        const val KEY_GET_RECIPES = "key_get_recipes"
        const val expirationTime = (60 * 10 * 1000).toLong()
    }

    override fun clearRecipes(): Completable {
        return Completable.defer {
            appDatabase.cachedRecipesDao().deleteRecipes()
            Completable.complete()
        }
    }

    override fun saveRecipes(recipes: List<RecipeEntity>): Completable {
        return Completable.defer {
            appDatabase.cachedRecipesDao().insertRecipes(
                recipes.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun getRecipes(): Flowable<List<RecipeEntity>> {
        return appDatabase.cachedRecipesDao().getRecipes()
            .map {
                it.map { cachedRecipe -> mapper.mapFromCached(cachedRecipe) }
            }
    }

    override fun getBookmarkedRecipes(): Flowable<List<RecipeEntity>> {
        return appDatabase.cachedRecipesDao().getBookmarkedRecipes()
            .map {
                it.map { cachedRecipe -> mapper.mapFromCached(cachedRecipe) }
            }
    }

    override fun setRecipeAsBookmarked(recipeId: Long): Completable {
        return Completable.defer {
            appDatabase.cachedRecipesDao().setBookmarkStatus(true, recipeId)
            Completable.complete()
        }
    }

    override fun setRecipeAsNotBookmarked(recipeId: Long): Completable {
        return Completable.defer {
            appDatabase.cachedRecipesDao().setBookmarkStatus(false, recipeId)
            Completable.complete()
        }
    }

    override fun areRecipesCached(): Single<Boolean> {
        return appDatabase.cachedRecipesDao().getRecipes().isEmpty
            .map {
                !it
            }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            appDatabase.configDao().insertConfig(Config(KEY_GET_RECIPES, "", lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isRecipesCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        return appDatabase.configDao().getConfig(KEY_GET_RECIPES)
            .onErrorReturn { Config("", "", 0) }
            .map { currentTime - it.lastCacheTime > expirationTime }
    }

}