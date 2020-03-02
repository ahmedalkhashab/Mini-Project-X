package io.android.projectx.domain.features.usermanagement.model

sealed class UserStatus {
    object Registered : UserStatus()
    object Anonymous : UserStatus()
}