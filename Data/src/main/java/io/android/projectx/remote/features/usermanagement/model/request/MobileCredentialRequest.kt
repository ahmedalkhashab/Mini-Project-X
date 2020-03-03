package io.android.projectx.remote.features.usermanagement.model.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileCredentialRequest(
    @SerializedName("mobile")
    var mobile: MobileNumber? = null,
    @SerializedName("password")
    var password: String? = null
) : Parcelable