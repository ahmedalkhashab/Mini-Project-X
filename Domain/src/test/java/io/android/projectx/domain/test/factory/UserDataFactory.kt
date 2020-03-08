package io.android.projectx.domain.test.factory

import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.domain.features.usermanagement.model.UserStatus

object UserDataFactory {

    fun makeUser(): User {
        return User(
            DataFactory.uniqueId(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            UserStatus.Anonymous
        )
    }

}