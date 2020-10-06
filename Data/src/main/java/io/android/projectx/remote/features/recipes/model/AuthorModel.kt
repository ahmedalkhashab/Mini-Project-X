package io.android.projectx.remote.features.recipes.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AuthorModel(
    @Json(name = "id") val authorId: Long,
    @Json(name = "author_name") val authorName: String,
    @Json(name = "author_avatar") val authorAvatar: String? = null
)
