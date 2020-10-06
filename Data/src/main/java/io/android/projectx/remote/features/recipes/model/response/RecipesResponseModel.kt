package io.android.projectx.remote.features.recipes.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.android.projectx.remote.features.recipes.model.RecipeModel

@JsonClass(generateAdapter = true)
class RecipesResponseModel(@Json(name = "content") val items: List<RecipeModel>? = null)