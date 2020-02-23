package io.android.projectx.data.features.recipes.store

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.data.features.recipes.repository.RecipesRemote
import io.android.projectx.data.test.factory.DataFactory
import io.android.projectx.data.test.factory.RecipeFactory
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RecipesRemoteDataStoreTest{

    private val remote = mock<RecipesRemote>()
    private val store =
        RecipesRemoteDataStore(remote)

    @Test
    fun getRecipesCompletes() {
        stubRemoteGetRecipes(Flowable.just(listOf(RecipeFactory.makeRecipeEntity())))
        val testObserver = store.getRecipes().test()
        testObserver.assertComplete()
    }

    @Test
    fun getRecipesReturnsData() {
        val response = listOf(RecipeFactory.makeRecipeEntity())
        stubRemoteGetRecipes(Flowable.just(response))
        val testObserver = store.getRecipes().test()
        testObserver.assertValue(response)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveRecipesThrowsException() {
        store.saveRecipes(listOf()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearRecipesThrowsException() {
        store.clearRecipes().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getBookmarkedRecipesThrowsException() {
        store.getBookmarkedRecipes().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setRecipeAsBookmarkedThrowsException() {
        store.setRecipeAsBookmarked(DataFactory.uniqueId()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setRecipeAsNotBookmarkedThrowsException() {
        store.setRecipeAsNotBookmarked(DataFactory.uniqueId()).test()
    }

    private fun stubRemoteGetRecipes(flowable: Flowable<List<RecipeEntity>>) {
        whenever(remote.getRecipes())
            .thenReturn(flowable)
    }

}