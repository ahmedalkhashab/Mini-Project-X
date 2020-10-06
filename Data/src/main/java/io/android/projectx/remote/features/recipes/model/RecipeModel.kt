package io.android.projectx.remote.features.recipes.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
class RecipeModel(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "video_url") val url: String? = null,
    @Json(name = "thumbnail_url") val urlToImage: String? = null,
    @Json(name = "approved_at") val publishedAt: Long? = null
)