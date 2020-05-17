package io.android.projectx.presentation.features

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import io.android.projectx.domain.features.recipes.interactor.bookmarked.BookmarkRecipe
import io.android.projectx.domain.features.recipes.interactor.bookmarked.UnBookmarkRecipe
import io.android.projectx.domain.features.recipes.interactor.browse.GetRecipes
import io.android.projectx.domain.features.recipes.model.Recipe
import io.android.projectx.presentation.features.recipes.browse.BrowseRecipesViewModel
import io.android.projectx.presentation.base.mapper.RecipeViewMapper
import io.android.projectx.presentation.base.model.RecipeView
import io.android.projectx.presentation.base.state.Resource.Status
import io.android.projectx.presentation.test.factory.DataFactory
import io.android.projectx.presentation.test.factory.RecipeFactory
import io.reactivex.observers.DisposableObserver
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Captor

class BrowseRecipesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    var getRecipes = mock<GetRecipes>()
    var bookmarkRecipe = mock<BookmarkRecipe>()
    var unBookmarkRecipe = mock<UnBookmarkRecipe>()
    var recipeMapper = mock<RecipeViewMapper>()
    var recipeViewModel =
        BrowseRecipesViewModel(
            getRecipes,
            bookmarkRecipe,
            unBookmarkRecipe,
            recipeMapper
        )

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Recipe>>>()

    @Test
    fun fetchRecipesExecutesUseCase() {
        recipeViewModel.fetchRecipes()

        verify(getRecipes, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchRecipesReturnsSuccess() {
        val recipes = RecipeFactory.makeRecipeList(2)
        val recipeViews = RecipeFactory.makeRecipeViewList(2)
        stubRecipeMapperMapToView(recipeViews[0], recipes[0])
        stubRecipeMapperMapToView(recipeViews[1], recipes[1])

        recipeViewModel.fetchRecipes()

        verify(getRecipes).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(recipes)

        assertEquals(
            Status.SUCCESS,
            recipeViewModel.getRecipes().value?.status
        )
    }

    @Test
    fun fetchRecipesReturnsData() {
        val recipes = RecipeFactory.makeRecipeList(2)
        val recipeViews = RecipeFactory.makeRecipeViewList(2)
        stubRecipeMapperMapToView(recipeViews[0], recipes[0])
        stubRecipeMapperMapToView(recipeViews[1], recipes[1])

        recipeViewModel.fetchRecipes()

        verify(getRecipes).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(recipes)

        assertEquals(
            recipeViews,
            recipeViewModel.getRecipes().value?.data
        )
    }

    @Test
    fun fetchRecipesReturnsError() {
        recipeViewModel.fetchRecipes()

        verify(getRecipes).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(
            Status.ERROR,
            recipeViewModel.getRecipes().value?.status
        )
    }

    @Test
    fun fetchRecipesReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        recipeViewModel.fetchRecipes()

        verify(getRecipes).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(
            errorMessage,
            recipeViewModel.getRecipes().value?.message
        )
    }

    private fun stubRecipeMapperMapToView(
        recipeView: RecipeView,
        recipe: Recipe
    ) {
        whenever(recipeMapper.mapToView(recipe))
            .thenReturn(recipeView)
    }

}