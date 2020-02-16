package io.android.projectx.remote.features.recipes.model.response

import com.google.gson.annotations.SerializedName
import io.android.projectx.remote.features.recipes.model.RecipeModel

class RecipesResponseModel(@SerializedName("content") val items: List<RecipeModel>)