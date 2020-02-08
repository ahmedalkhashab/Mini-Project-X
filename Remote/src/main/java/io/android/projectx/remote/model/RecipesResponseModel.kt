package io.android.projectx.remote.model

import com.google.gson.annotations.SerializedName

class RecipesResponseModel(@SerializedName("content") val items: List<RecipeModel>)