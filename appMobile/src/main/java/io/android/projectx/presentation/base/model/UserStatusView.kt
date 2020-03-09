package io.android.projectx.presentation.base.model

sealed class UserStatusView {
    object Registered : UserStatusView()
    object Anonymous : UserStatusView()
}