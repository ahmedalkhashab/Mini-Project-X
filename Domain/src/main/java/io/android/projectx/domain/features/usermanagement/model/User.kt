package io.android.projectx.domain.features.usermanagement.model

class User(
    val id: Long, val name: String, val email: String, val mobile: String,
    val userStatus: UserStatus
)