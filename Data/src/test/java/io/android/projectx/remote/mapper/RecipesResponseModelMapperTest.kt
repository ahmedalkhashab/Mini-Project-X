package io.android.projectx.remote.mapper

import io.android.projectx.remote.test.factory.RecipeDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class RecipesResponseModelMapperTest {

    private val mapper = RecipesResponseModelMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = RecipeDataFactory.makeRecipe()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.id, entity.id)
        assertEquals(model.author.authorName, entity.author)
        assertEquals(model.title, entity.title)
        assertEquals(model.description, entity.description)
        assertEquals(model.url, entity.url)
        assertEquals(model.urlToImage, entity.urlToImage)
        assertEquals(model.publishedAt, entity.publishedAt)
        assertEquals(model.content, entity.content)
    }
}