package io.android.projectx.data.features.usermanagement.repository

import io.android.projectx.data.features.usermanagement.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

interface UserManagementCache : UserManagementDataStore {

    fun clearUser(): Completable

    fun saveUser(userEntity: UserEntity, lastCache: Long): Completable

    fun areUserCached(): Single<Boolean>
}