package io.android.projectx.data.features.usermanagement.repository

import io.android.projectx.data.features.usermanagement.model.UserEntity
import io.reactivex.Single

interface UserManagementCache : UserManagementDataStore {

    fun getUser(): UserEntity

    fun clearUser()

    fun saveUser(userEntity: UserEntity, lastCache: Long)

    fun areUserCached(): Boolean

    fun persistToken(key: String, value: String)

    fun clearToken(key: String)

    fun getToken(key: String): Single<String>

}