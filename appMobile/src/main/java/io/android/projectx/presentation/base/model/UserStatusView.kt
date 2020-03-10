package io.android.projectx.presentation.base.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class UserStatusView : Parcelable {
    @Parcelize
    object Registered : UserStatusView()
    @Parcelize
    object Anonymous : UserStatusView()
}