package io.android.projectx.cache.mapper

import io.android.projectx.cache.model.CachedRecipe
import io.android.projectx.cache.test.factory.RecipeDataFactory
import io.android.projectx.data.model.RecipeEntity
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CachedRecipeMapperTest {

    private val mapper = CachedRecipeMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = RecipeDataFactory.makeCachedRecipe()
        val entity = mapper.mapFromCached(model)

        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = RecipeDataFactory.makeRecipeEntity()
        val model = mapper.mapToCached(entity)

        assertEqualData(model, entity)
    }

    private fun assertEqualData(
        model: CachedRecipe,
        entity: RecipeEntity
    ) {
        assertEquals(model.id, entity.id)
        assertEquals(model.author, entity.author)
        assertEquals(model.title, entity.title)
        assertEquals(model.description, entity.description)
        assertEquals(model.url, entity.url)
        assertEquals(model.urlToImage, entity.urlToImage)
        assertEquals(model.publishedAt, entity.publishedAt.getOffsetDate())
        assertEquals(model.content, entity.content)
        assertEquals(model.isBookmarked, entity.isBookmarked)
    }

}