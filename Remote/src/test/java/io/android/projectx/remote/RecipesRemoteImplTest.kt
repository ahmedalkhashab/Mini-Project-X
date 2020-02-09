package io.android.projectx.remote

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.android.projectx.data.model.RecipeEntity
import io.android.projectx.remote.mapper.RecipesResponseModelMapper
import io.android.projectx.remote.model.RecipeModel
import io.android.projectx.remote.model.RecipesResponseModel
import io.android.projectx.remote.service.RecipesService
import io.android.projectx.remote.test.factory.RecipeDataFactory
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RecipesRemoteImplTest {

    private val mapper = mock<RecipesResponseModelMapper>()
    private val service = mock<RecipesService>()
    private val remote = RecipesRemoteImpl(service, mapper)

    @Test
    fun getRecipesCompletes() {
        stubRecipesServiceSearch(Flowable.just(RecipeDataFactory.makeRecipesResponse()))
        stubRecipesResponseModelMapperMapFromModel(any(), RecipeDataFactory.makeRecipeEntity())

        val testObserver = remote.getRecipes().test()
        testObserver.assertComplete()
    }

    @Test
    fun getRecipesCallServer() {
        stubRecipesServiceSearch(Flowable.just(RecipeDataFactory.makeRecipesResponse()))
        stubRecipesResponseModelMapperMapFromModel(any(), RecipeDataFactory.makeRecipeEntity())

        remote.getRecipes().test()
        verify(service).searchRepositories(any())
    }

    @Test
    fun getRecipesReturnsData() {
        val response = RecipeDataFactory.makeRecipesResponse()
        stubRecipesServiceSearch(Flowable.just(response))
        val entities = mutableListOf<RecipeEntity>()
        response.items.forEach {
            val entity = RecipeDataFactory.makeRecipeEntity()
            entities.add(entity)
            stubRecipesResponseModelMapperMapFromModel(it, entity)
        }
        val testObserver = remote.getRecipes().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getRecipesCallServerWithCorrectParameters() {
        stubRecipesServiceSearch(Flowable.just(RecipeDataFactory.makeRecipesResponse()))
        stubRecipesResponseModelMapperMapFromModel(any(), RecipeDataFactory.makeRecipeEntity())

        remote.getRecipes().test()
        //todo - retest this case after moving parameters
        //verify(service).searchRepositories(1)
        verify(service).searchRepositories(1)
    }

    private fun stubRecipesServiceSearch(flowable: Flowable<RecipesResponseModel>) {
        whenever(service.searchRepositories(any()))
            .thenReturn(flowable)
    }

    private fun stubRecipesResponseModelMapperMapFromModel(
        model: RecipeModel,
        entity: RecipeEntity
    ) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(entity)
    }

}