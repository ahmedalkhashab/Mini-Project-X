package io.android.projectx.remote.features.recipes.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
class RecipeModel(
    @Json(name = "id") val id: Long,
    @Json(name = "author") val author: AuthorModel,
    @Json(name = "title") val title: String,
    @Json(name = "method_description") val description: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "url_to_image") val urlToImage: String? = null,
    @Json(name = "published_at") val publishedAt: Date? = null,
    @Json(name = "ingredients") val content: String? = null
)