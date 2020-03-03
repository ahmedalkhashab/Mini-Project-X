package io.android.projectx.data.features.usermanagement.repository

import io.android.projectx.data.features.usermanagement.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface UserManagementRemote : UserManagementDataStore {

    fun login(email: String, password: String): Observable<UserEntity>

    fun login(countryCode: String, mobileNumber: String, password: String): Observable<UserEntity>

    // we can use otp instead of password
    fun verifyByMobile(countryCode: String, mobileNumber: String, pinCode: String): Observable<UserEntity>

    // we can use otp instead of password
    fun verifyByEmail(email: String, pinCode: String): Observable<UserEntity>

    fun forgetPassword(email: String): Completable

    fun resetPassword(email: String, pinCode: String, password: String): Observable<UserEntity>

    fun signUp(email: String, password: String): Observable<UserEntity>

    fun signUp(countryCode: String, mobileNumber: String, password: String): Observable<UserEntity>

}