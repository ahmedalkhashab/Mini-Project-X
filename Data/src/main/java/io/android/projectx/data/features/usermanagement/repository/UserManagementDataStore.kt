package io.android.projectx.data.features.usermanagement.repository

import io.reactivex.Completable

interface UserManagementDataStore {

    fun forceLogout(): Completable

    fun logout(): Completable

    fun logout(email: String): Completable

    fun logout(countryCode: String, mobileNumber: String): Completable

}