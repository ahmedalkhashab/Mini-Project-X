package io.android.projectx.cache.recipes.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import io.android.projectx.cache.recipes.db.RecipesDatabase
import io.android.projectx.cache.test.factory.RecipeDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CachedRecipesDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        RecipesDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getRecipesReturnsData() {
        val recipe = RecipeDataFactory.makeCachedRecipe()
        database.cachedRecipesDao().insertRecipes(listOf(recipe))

        val testObserver = database.cachedRecipesDao().getRecipes().test()
        testObserver.assertValue(listOf(recipe))
    }

    @Test
    fun deleteRecipesClearsData() {
        val recipe = RecipeDataFactory.makeCachedRecipe()
        database.cachedRecipesDao().insertRecipes(listOf(recipe))
        database.cachedRecipesDao().deleteRecipes()

        val testObserver = database.cachedRecipesDao().getRecipes().test()
        testObserver.assertValue(emptyList())
    }

    @Test
    fun getBookmarkedRecipesReturnsData() {
        val recipe = RecipeDataFactory.makeCachedRecipe()
        val bookmarkedRecipe = RecipeDataFactory.makeBookmarkedCachedRecipe()
        database.cachedRecipesDao().insertRecipes(listOf(recipe, bookmarkedRecipe))

        val testObserver = database.cachedRecipesDao().getBookmarkedRecipes().test()
        testObserver.assertValue(listOf(bookmarkedRecipe))
    }

    @Test
    fun setRecipeAsBookmarkedSavesData() {
        val recipe = RecipeDataFactory.makeCachedRecipe()
        database.cachedRecipesDao().insertRecipes(listOf(recipe))
        database.cachedRecipesDao().setBookmarkStatus(true, recipe.id)
        recipe.isBookmarked = true

        val testObserver = database.cachedRecipesDao().getBookmarkedRecipes().test()
        testObserver.assertValue(listOf(recipe))
    }

    @Test
    fun setRecipeAsNotBookmarkedSavesData() {
        val recipe = RecipeDataFactory.makeBookmarkedCachedRecipe()
        database.cachedRecipesDao().insertRecipes(listOf(recipe))
        database.cachedRecipesDao().setBookmarkStatus(false, recipe.id)
        recipe.isBookmarked = false

        val testObserver = database.cachedRecipesDao().getBookmarkedRecipes().test()
        testObserver.assertValue(emptyList())
    }

}