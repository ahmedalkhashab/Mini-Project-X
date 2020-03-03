package io.android.projectx.remote.features.usermanagement.model.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileNumber(
    @SerializedName("countryCode")
    var countryCode: String,
    @SerializedName("number")
    var number: String
) : Parcelable {
    fun format(): String = countryCode.plus(number)
}