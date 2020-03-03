package io.android.projectx.remote.features.usermanagement.model.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmailCredentialRequest(
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("password")
    var password: String? = null
) : Parcelable