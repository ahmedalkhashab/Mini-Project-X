package io.android.projectx.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

class RecipeModel(
    @SerializedName("id") val id: Long,
    @SerializedName("author") val author: AuthorModel,
    @SerializedName("title") val title: String,
    @SerializedName("method_description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("url_to_image") val urlToImage: String,
    @SerializedName("published_at") val publishedAt: Date,
    @SerializedName("ingredients") val content: String
)