package io.android.projectx.data.features.recipes

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.android.projectx.data.features.recipes.mapper.RecipeMapper
import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.data.features.recipes.repository.RecipesCache
import io.android.projectx.data.features.recipes.repository.RecipesDataStore
import io.android.projectx.data.features.recipes.store.RecipesDataStoreFactory
import io.android.projectx.data.test.factory.RecipeFactory
import io.android.projectx.domain.features.recipes.model.Recipe
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RecipesDataRepositoryTest {

    private val mapper = mock<RecipeMapper>()
    private val factory = mock<RecipesDataStoreFactory>()
    private val store = mock<RecipesDataStore>()
    private val cache = mock<RecipesCache>()
    private val repository =
        RecipesDataRepository(
            mapper,
            cache,
            factory
        )

    @Before
    fun setUp() {
        stubFactoryGetDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreRecipesCached(Single.just(false))
    }

    @Test
    fun getRecipesCompletes() {
        stubGetRecipes(Flowable.just(listOf(RecipeFactory.makeRecipeEntity())))
        stubMapper(RecipeFactory.makeRecipe(), any())
        val testObserver = repository.getRecipes().test()
        testObserver.assertComplete()
    }

    @Test
    fun getRecipesReturnsData() {
        val recipeEntity = RecipeFactory.makeRecipeEntity()
        val recipe = RecipeFactory.makeRecipe()
        stubGetRecipes(Flowable.just(listOf(recipeEntity)))
        stubMapper(recipe, recipeEntity)
        val testObserver = repository.getRecipes().test()
        testObserver.assertValue(listOf(recipe))
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isRecipesCacheExpired())
            .thenReturn(single)
    }

    private fun stubAreRecipesCached(single: Single<Boolean>) {
        whenever(cache.areRecipesCached())
            .thenReturn(single)
    }

    private fun stubMapper(model: Recipe, entity: RecipeEntity) {
        whenever(mapper.mapFromEntity(entity))
            .thenReturn(model)
    }

    private fun stubGetRecipes(flowable: Flowable<List<RecipeEntity>>) {
        whenever(store.getRecipes())
            .thenReturn(flowable)
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
            .thenReturn(store)
    }

}