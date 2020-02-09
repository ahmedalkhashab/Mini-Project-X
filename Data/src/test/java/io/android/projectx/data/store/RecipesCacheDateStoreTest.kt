package io.android.projectx.data.store

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.android.projectx.data.model.RecipeEntity
import io.android.projectx.data.repository.RecipesCache
import io.android.projectx.data.test.factory.DataFactory
import io.android.projectx.data.test.factory.RecipeFactory
import io.reactivex.Completable
import io.reactivex.Flowable

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RecipesCacheDateStoreTest {

    private val cache = mock<RecipesCache>()
    private val store = RecipesCacheDateStore(cache)

    @Test
    fun getRecipeCompletes() {
        stubRecipesCacheGetRecipes(Flowable.just(listOf(RecipeFactory.makeRecipeEntity())))
        val testObserver = store.getRecipes().test()
        testObserver.assertComplete()
    }

    @Test
    fun getRecipesReturnsData() {
        val data = listOf(RecipeFactory.makeRecipeEntity())
        stubRecipesCacheGetRecipes(Flowable.just(data))
        val testObserver = store.getRecipes().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getRecipesCallsCacheSource() {
        stubRecipesCacheGetRecipes(Flowable.just(listOf(RecipeFactory.makeRecipeEntity())))
        store.getRecipes().test()
        verify(cache).getRecipes()
    }

    @Test
    fun saveRecipesCompletes() {
        stubRecipesCacheSaveRecipes(Completable.complete())
        stubRecipesCacheSetLastCacheTime(Completable.complete())
        val testObserver = store.saveRecipes(listOf(RecipeFactory.makeRecipeEntity())).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveRecipesCallsCacheStore() {
        val data = listOf(RecipeFactory.makeRecipeEntity())
        stubRecipesCacheSaveRecipes(Completable.complete())
        stubRecipesCacheSetLastCacheTime(Completable.complete())
        store.saveRecipes(data).test()
        verify(cache).saveRecipes(data)
    }

    @Test
    fun clearRecipesCompletes() {
        stubRecipesClearRecipes(Completable.complete())
        val testObserver = store.clearRecipes().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearRecipesCallsCacheStore() {
        stubRecipesClearRecipes(Completable.complete())
        store.clearRecipes().test()
        verify(cache).clearRecipes()
    }

    @Test
    fun getBookmarkedRecipeCompletes() {
        stubRecipesCacheGetBookmarkedRecipes(Flowable.just(listOf(RecipeFactory.makeRecipeEntity())))
        val testObserver = store.getBookmarkedRecipes().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedRecipesReturnsData() {
        val data = listOf(RecipeFactory.makeRecipeEntity())
        stubRecipesCacheGetBookmarkedRecipes(Flowable.just(data))
        val testObserver = store.getBookmarkedRecipes().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getBookmarkedRecipesCallsCacheSource() {
        stubRecipesCacheGetBookmarkedRecipes(Flowable.just(listOf(RecipeFactory.makeRecipeEntity())))
        store.getBookmarkedRecipes().test()
        verify(cache).getBookmarkedRecipes()
    }

    @Test
    fun setRecipeAsBookmarkedCompletes() {
        stubRecipesCacheSetRecipesAsBookmarked(Completable.complete())
        val testObserver = store.setRecipeAsBookmarked(DataFactory.uniqueId()).test()
        testObserver.assertComplete()
    }

    @Test
    fun setRecipeAsBookmarkedCallsCacheSource() {
        val recipeId = DataFactory.uniqueId()
        stubRecipesCacheSetRecipesAsBookmarked(Completable.complete())
        store.setRecipeAsBookmarked(recipeId).test()
        verify(cache).setRecipeAsBookmarked(recipeId)
    }

    @Test
    fun setRecipeAsNotBookmarkedCompletes() {
        stubRecipesCacheSetRecipesAsNotBookmarked(Completable.complete())
        val testObserver = store.setRecipeAsNotBookmarked(DataFactory.uniqueId()).test()
        testObserver.assertComplete()
    }

    @Test
    fun setRecipeAsNotBookmarkedCallsCacheSource() {
        val recipeId = DataFactory.uniqueId()
        stubRecipesCacheSetRecipesAsNotBookmarked(Completable.complete())
        store.setRecipeAsNotBookmarked(recipeId).test()
        verify(cache).setRecipeAsNotBookmarked(recipeId)
    }

    private fun stubRecipesCacheGetRecipes(flowable: Flowable<List<RecipeEntity>>) {
        whenever(cache.getRecipes())
            .thenReturn(flowable)
    }

    private fun stubRecipesCacheSaveRecipes(completable: Completable) {
        whenever(cache.saveRecipes(any()))
            .thenReturn(completable)
    }

    private fun stubRecipesCacheSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any()))
            .thenReturn(completable)
    }

    private fun stubRecipesClearRecipes(completable: Completable) {
        whenever(cache.clearRecipes())
            .thenReturn(completable)
    }

    private fun stubRecipesCacheGetBookmarkedRecipes(flowable: Flowable<List<RecipeEntity>>) {
        whenever(cache.getBookmarkedRecipes())
            .thenReturn(flowable)
    }

    private fun stubRecipesCacheSetRecipesAsBookmarked(completable: Completable) {
        whenever(cache.setRecipeAsBookmarked(any()))
            .thenReturn(completable)
    }

    private fun stubRecipesCacheSetRecipesAsNotBookmarked(completable: Completable) {
        whenever(cache.setRecipeAsNotBookmarked(any()))
            .thenReturn(completable)
    }

}