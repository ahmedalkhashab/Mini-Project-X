package io.android.projectx.remote.features.usermanagement.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MobileCredentialRequest(
    @Json(name = "mobile")
    var mobile: MobileNumber? = null,
    @Json(name = "password")
    var password: String? = null
)