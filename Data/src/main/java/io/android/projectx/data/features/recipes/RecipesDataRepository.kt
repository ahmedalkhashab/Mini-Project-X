package io.android.projectx.data.features.recipes

import io.android.projectx.data.features.recipes.mapper.RecipeMapper
import io.android.projectx.data.features.recipes.repository.RecipesCache
import io.android.projectx.data.features.recipes.store.RecipesDataStoreFactory
import io.android.projectx.domain.features.recipes.model.Recipe
import io.android.projectx.domain.features.recipes.repository.RecipesRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class RecipesDataRepository @Inject constructor(
    private val mapper: RecipeMapper,
    private val cache: RecipesCache,
    private val factory: RecipesDataStoreFactory
) : RecipesRepository {

    override fun getRecipes(): Observable<List<Recipe>> {
        return Observable.zip(cache.areRecipesCached().toObservable(),
            cache.isRecipesCacheExpired().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                Pair(areCached, isExpired)
            })
            .flatMap {
                factory.getDataStore(it.first, it.second).getRecipes().toObservable()
                    .distinctUntilChanged()
            }
            .flatMap { recipes ->
                factory.getCacheDataStore()
                    .saveRecipes(recipes)
                    .andThen(Observable.just(recipes))
            }
            .map {
                it.map { mapper.mapFromEntity(it) }
            }
    }

    override fun bookmarkRecipe(recipeId: Long): Completable {
        return factory.getCacheDataStore().setRecipeAsBookmarked(recipeId)
    }

    override fun unBookmarkRecipe(recipeId: Long): Completable {
        return factory.getCacheDataStore().setRecipeAsNotBookmarked(recipeId)
    }

    override fun getBookmarkedRecipes(): Observable<List<Recipe>> {
        return factory.getCacheDataStore().getBookmarkedRecipes().toObservable()
            .map { it.map { mapper.mapFromEntity(it) } }
    }

}