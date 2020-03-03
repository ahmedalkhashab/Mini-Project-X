package io.android.projectx.data.features.usermanagement.model

class UserEntity(
    val id: Long, val name: String, val email: String, val mobile: String,
    val userStatus: UserStatusEntity
)