package io.android.projectx.presentation.features.cloudmessaging

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class CaseStatus : Parcelable {
    @Parcelize
    object Status1 : CaseStatus()

    @Parcelize
    object Status2 : CaseStatus()

    @Parcelize
    object Status3 : CaseStatus()

    @Parcelize
    object Status4 : CaseStatus()

    override fun toString(): String {
        return super.toString()
    }
}