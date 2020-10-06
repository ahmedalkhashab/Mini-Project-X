package io.android.projectx.remote.features.recipes.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.android.projectx.remote.features.recipes.model.RecipeModel

@JsonClass(generateAdapter = true)
class RecipesResponseModel(
    @Json(name = "count") val count: Long,
    @Json(name = "results") val items: List<RecipeModel>? = null
)