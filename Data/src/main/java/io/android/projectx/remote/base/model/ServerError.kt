package io.android.projectx.remote.base.model

import com.google.gson.annotations.SerializedName

data class ServerError(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)