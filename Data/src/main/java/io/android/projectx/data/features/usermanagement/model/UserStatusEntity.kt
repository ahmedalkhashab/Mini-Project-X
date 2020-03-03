package io.android.projectx.data.features.usermanagement.model

sealed class UserStatusEntity {
    object Registered : UserStatusEntity()
    object Anonymous : UserStatusEntity()
}