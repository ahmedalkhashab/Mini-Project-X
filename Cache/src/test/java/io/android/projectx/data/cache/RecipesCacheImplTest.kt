package io.android.projectx.data.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import io.android.projectx.data.cache.db.RecipesDatabase
import io.android.projectx.data.cache.mapper.CachedRecipeMapper
import io.android.projectx.data.cache.test.factory.RecipeDataFactory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RecipesCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        RecipesDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()
    private val entityMapper = CachedRecipeMapper()
    private val cache = RecipesCacheImpl(database, entityMapper)

    @Test
    fun clearTablesCompletes() {
        val testObserver = cache.clearRecipes().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveRecipesCompletes() {
        val recipes = listOf(RecipeDataFactory.makeRecipeEntity())

        val testObserver = cache.saveRecipes(recipes).test()
        testObserver.assertComplete()
    }

    @Test
    fun getRecipesReturnsData() {
        val recipes = listOf(RecipeDataFactory.makeRecipeEntity())
        cache.saveRecipes(recipes).test()

        val testObserver = cache.getRecipes().test()
        testObserver.assertValue(recipes)
    }

    @Test
    fun getBookmarkedRecipesReturnsData() {
        val bookmarkedRecipe = RecipeDataFactory.makeBookmarkedRecipeEntity()
        val recipes = listOf(
            RecipeDataFactory.makeRecipeEntity(),
            bookmarkedRecipe
        )
        cache.saveRecipes(recipes).test()

        val testObserver = cache.getBookmarkedRecipes().test()
        testObserver.assertValue(listOf(bookmarkedRecipe))
    }

    @Test
    fun setRecipeAsBookmarkedCompletes() {
        val recipes = listOf(RecipeDataFactory.makeRecipeEntity())
        cache.saveRecipes(recipes).test()

        val testObserver = cache.setRecipeAsBookmarked(recipes[0].id).test()
        testObserver.assertComplete()
    }

    @Test
    fun setRecipeAsNotBookmarkedCompletes() {
        val recipes = listOf(RecipeDataFactory.makeBookmarkedRecipeEntity())
        cache.saveRecipes(recipes).test()

        val testObserver = cache.setRecipeAsNotBookmarked(recipes[0].id).test()
        testObserver.assertComplete()
    }

    @Test
    fun areRecipesCacheReturnsData() {
        val recipes = listOf(RecipeDataFactory.makeRecipeEntity())
        cache.saveRecipes(recipes).test()

        val testObserver = cache.areRecipesCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTimeCompletes() {
        val testObserver = cache.setLastCacheTime(1000L).test()
        testObserver.assertComplete()
    }

    @Test
    fun isRecipesCacheExpiredReturnsNotExpired() {
        cache.setLastCacheTime(1000L).test()
        val testObserver = cache.isRecipesCacheExpired().test()
        testObserver.assertValue(false)
    }

}