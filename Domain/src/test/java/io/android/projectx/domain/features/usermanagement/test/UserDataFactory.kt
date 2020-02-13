package io.android.projectx.domain.features.usermanagement.test

import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.domain.test.DataFactory

object UserDataFactory {

    fun makeUser(): User {
        return User(
            DataFactory.uniqueId(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomBoolean()
        )
    }

}