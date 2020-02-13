package io.android.projectx.presentation.features

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import io.android.projectx.domain.interactor.bookmarked.GetBookmarkedRecipes
import io.android.projectx.domain.model.Recipe
import io.android.projectx.presentation.features.bookmarked.BookmarkedRecipesViewModel
import io.android.projectx.presentation.mapper.RecipeViewMapper
import io.android.projectx.presentation.model.RecipeView
import io.android.projectx.presentation.state.ResourceState
import io.android.projectx.presentation.test.factory.DataFactory
import io.android.projectx.presentation.test.factory.RecipeFactory
import io.reactivex.observers.DisposableObserver
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

@RunWith(JUnit4::class)
class BookmarkedRecipesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    var getBookmarkedRecipes = mock<GetBookmarkedRecipes>()
    var mapper = mock<RecipeViewMapper>()
    var recipeViewModel =
        BookmarkedRecipesViewModel(
            getBookmarkedRecipes, mapper
        )

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Recipe>>>()

    @Test
    fun fetchRecipesExecutesUseCase() {
        recipeViewModel.fetchBookmarkedRecipes()

        verify(getBookmarkedRecipes, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchRecipesReturnsSuccess() {
        val recipes = RecipeFactory.makeRecipeList(2)
        val recipeViews = RecipeFactory.makeRecipeViewList(2)
        stubRecipeMapperMapToView(recipeViews[0], recipes[0])
        stubRecipeMapperMapToView(recipeViews[1], recipes[1])

        recipeViewModel.fetchBookmarkedRecipes()

        verify(getBookmarkedRecipes).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(recipes)

        assertEquals(
            ResourceState.SUCCESS,
            recipeViewModel.getBookmarkedRecipes().value?.status
        )
    }

    @Test
    fun fetchRecipesReturnsData() {
        val recipes = RecipeFactory.makeRecipeList(2)
        val recipeViews = RecipeFactory.makeRecipeViewList(2)
        stubRecipeMapperMapToView(recipeViews[0], recipes[0])
        stubRecipeMapperMapToView(recipeViews[1], recipes[1])

        recipeViewModel.fetchBookmarkedRecipes()

        verify(getBookmarkedRecipes).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(recipes)

        assertEquals(
            recipeViews,
            recipeViewModel.getBookmarkedRecipes().value?.data
        )
    }

    @Test
    fun fetchRecipesReturnsError() {
        recipeViewModel.fetchBookmarkedRecipes()

        verify(getBookmarkedRecipes).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(
            ResourceState.ERROR,
            recipeViewModel.getBookmarkedRecipes().value?.status
        )
    }

    @Test
    fun fetchRecipesReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        recipeViewModel.fetchBookmarkedRecipes()

        verify(getBookmarkedRecipes).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(
            errorMessage,
            recipeViewModel.getBookmarkedRecipes().value?.message
        )
    }

    private fun stubRecipeMapperMapToView(
        recipeView: RecipeView,
        recipe: Recipe
    ) {
        whenever(mapper.mapToView(recipe))
            .thenReturn(recipeView)
    }

}