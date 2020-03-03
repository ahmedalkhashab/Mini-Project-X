package io.android.projectx.domain.features.usermanagement.repository

import io.android.projectx.domain.features.usermanagement.model.User
import io.reactivex.Completable
import io.reactivex.Observable

interface UserManagementRepository {

    fun login(email: String, password: String): Observable<User>

    fun login(countryCode: String, mobileNumber: String, password: String): Observable<User>

    // we can use otp instead of password
    fun verifyByMobile(countryCode: String, mobileNumber: String, pinCode: String): Observable<User>

    // we can use otp instead of password
    fun verifyByEmail(email: String, pinCode: String): Observable<User>

    fun forgetPassword(email: String): Completable

    fun resetPassword(email: String, pinCode: String, password: String): Observable<User>

    fun signUp(email: String, password: String): Observable<User>

    fun signUp(countryCode: String, mobileNumber: String, password: String): Observable<User>

    fun logout(email: String): Completable

    fun logout(countryCode: String, mobileNumber: String): Completable

    fun getUser(): Observable<User>
}