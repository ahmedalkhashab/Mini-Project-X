package io.android.projectx.cache.recipes

import io.android.projectx.cache.recipes.db.RecipesDatabase
import io.android.projectx.cache.recipes.mapper.CachedRecipeMapper
import io.android.projectx.cache.recipes.model.Config
import io.android.projectx.data.model.RecipeEntity
import io.android.projectx.data.repository.RecipesCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RecipesCacheImpl @Inject constructor(
    private val recipesDatabase: RecipesDatabase,
    private val mapper: CachedRecipeMapper)
    : RecipesCache {

    override fun clearRecipes(): Completable {
        return Completable.defer {
            recipesDatabase.cachedRecipesDao().deleteRecipes()
            Completable.complete()
        }
    }

    override fun saveRecipes(recipes: List<RecipeEntity>): Completable {
        return Completable.defer {
            recipesDatabase.cachedRecipesDao().insertRecipes(
                recipes.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun getRecipes(): Flowable<List<RecipeEntity>> {
        return recipesDatabase.cachedRecipesDao().getRecipes()
            .map {
                it.map { cachedRecipe -> mapper.mapFromCached(cachedRecipe) }
            }
    }

    override fun getBookmarkedRecipes(): Flowable<List<RecipeEntity>> {
        return recipesDatabase.cachedRecipesDao().getBookmarkedRecipes()
            .map {
                it.map { cachedRecipe -> mapper.mapFromCached(cachedRecipe) }
            }
    }

    override fun setRecipeAsBookmarked(recipeId: Long): Completable {
        return Completable.defer {
            recipesDatabase.cachedRecipesDao().setBookmarkStatus(true, recipeId)
            Completable.complete()
        }
    }

    override fun setRecipeAsNotBookmarked(recipeId: Long): Completable {
        return Completable.defer {
            recipesDatabase.cachedRecipesDao().setBookmarkStatus(false, recipeId)
            Completable.complete()
        }
    }

    override fun areRecipesCached(): Single<Boolean> {
        return recipesDatabase.cachedRecipesDao().getRecipes().isEmpty
            .map {
                !it
            }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            recipesDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isRecipesCacheExpired(): Flowable<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return recipesDatabase.configDao().getConfig()
            .onErrorReturn { Config(lastCacheTime = 0) }
            .map {
                currentTime - it.lastCacheTime > expirationTime
            }
    }

}