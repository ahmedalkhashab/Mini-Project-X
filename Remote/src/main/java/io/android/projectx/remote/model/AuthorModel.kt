package io.android.projectx.remote.model

import com.google.gson.annotations.SerializedName

class AuthorModel(
    @SerializedName("id") val authorId: Long,
    @SerializedName("author_name") val authorName: String,
    @SerializedName("author_avatar") val authorAvatar: String
)
