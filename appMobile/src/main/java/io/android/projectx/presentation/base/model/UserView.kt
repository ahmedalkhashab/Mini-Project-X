package io.android.projectx.presentation.base.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserView(
    val id: Long, val name: String, val email: String, val mobile: String,
    val userStatus: UserStatusView
) : Parcelable