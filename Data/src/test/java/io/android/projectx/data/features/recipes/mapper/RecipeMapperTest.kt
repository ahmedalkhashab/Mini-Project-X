package io.android.projectx.data.features.recipes.mapper

import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.data.test.factory.RecipeFactory
import io.android.projectx.domain.features.recipes.model.Recipe
import org.junit.Test

import org.junit.Assert.*

class RecipeMapperTest {

    private val mapper = RecipeMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = RecipeFactory.makeRecipeEntity()
        val model = mapper.mapFromEntity(entity)
        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = RecipeFactory.makeRecipe()
        val entity = mapper.mapToEntity(model)
        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: RecipeEntity, model: Recipe) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.author, model.author)
        assertEquals(entity.title, model.title)
        assertEquals(entity.description, model.description)
        assertEquals(entity.url, model.url)
        assertEquals(entity.content, model.content)
        assertEquals(entity.publishedAt, model.publishedAt)
        assertEquals(entity.urlToImage, model.urlToImage)
    }

}