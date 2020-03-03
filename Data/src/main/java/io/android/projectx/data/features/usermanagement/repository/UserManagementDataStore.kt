package io.android.projectx.data.features.usermanagement.repository

import io.android.projectx.data.features.usermanagement.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface UserManagementDataStore {

    fun logout(email: String): Completable

    fun logout(countryCode: String, mobileNumber: String): Completable

    fun getUser(): Observable<UserEntity>

}