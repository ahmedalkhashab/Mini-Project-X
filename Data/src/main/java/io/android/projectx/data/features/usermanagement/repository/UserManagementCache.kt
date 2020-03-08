package io.android.projectx.data.features.usermanagement.repository

import io.android.projectx.data.features.usermanagement.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface UserManagementCache : UserManagementDataStore {

    fun getUser(): Observable<UserEntity>

    fun clearUser(): Completable

    fun saveUser(userEntity: UserEntity, lastCache: Long): Completable

    fun areUserCached(): Single<Boolean>

    fun persistToken(key: String, value: String): Completable

    fun clearToken(key: String): Completable

    fun getToken(key: String): Single<String>

}