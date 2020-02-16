package io.android.projectx.presentation.base.mapper

import io.android.projectx.presentation.test.factory.RecipeFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RecipeViewMapperTest {

    private val mapper = RecipeViewMapper()

    @Test
    fun mapToViewMapsData() {
        val recipe = RecipeFactory.makeRecipe()
        val recipeView = mapper.mapToView(recipe)

        assertEquals(recipe.id, recipeView.id)
        assertEquals(recipe.author, recipeView.author)
        assertEquals(recipe.title, recipeView.title)
        assertEquals(recipe.description, recipeView.description)
        assertEquals(recipe.url, recipeView.url)
        assertEquals(recipe.urlToImage, recipeView.urlToImage)
        assertEquals(recipe.publishedAt, recipeView.publishedAt)
        assertEquals(recipe.content, recipeView.content)
        assertEquals(recipe.isBookmarked, recipeView.isBookmarked)
    }

}