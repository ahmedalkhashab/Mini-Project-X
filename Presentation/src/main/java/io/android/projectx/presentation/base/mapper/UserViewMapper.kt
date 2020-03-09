package io.android.projectx.presentation.base.mapper

import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.domain.features.usermanagement.model.UserStatus
import io.android.projectx.presentation.base.model.UserStatusView
import io.android.projectx.presentation.base.model.UserView
import javax.inject.Inject

class UserViewMapper @Inject constructor() : Mapper<UserView, User> {

    override fun mapToView(type: User): UserView {
        return UserView(
            id = type.id,
            name = type.name,
            email = type.email,
            mobile = type.mobile,
            userStatus = when (type.userStatus) {
                UserStatus.Registered -> UserStatusView.Registered
                UserStatus.Anonymous -> UserStatusView.Anonymous
            }
        )
    }

}