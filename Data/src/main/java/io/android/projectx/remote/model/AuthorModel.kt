package io.android.projectx.remote.model

import com.google.gson.annotations.SerializedName

class AuthorModel(
    @SerializedName("author_id") val authorId: String,
    @SerializedName("author_name") val authorName: String,
    @SerializedName("author_avatar_url") val authorAvatar: String
)
