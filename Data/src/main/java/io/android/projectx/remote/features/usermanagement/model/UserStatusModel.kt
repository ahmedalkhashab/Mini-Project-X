package io.android.projectx.remote.features.usermanagement.model

sealed class UserStatusModel {
    object Registered : UserStatusModel()
    object Anonymous : UserStatusModel()
}