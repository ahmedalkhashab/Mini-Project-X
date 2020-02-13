package io.android.projectx.domain.features.usermanagement.repository

import io.android.projectx.domain.features.usermanagement.model.User
import io.reactivex.Completable
import io.reactivex.Observable

interface UserManagementRepository {

    fun login(email: String, password: String): Observable<User>

    fun forgetPassword(email: String): Completable

    fun verifyByEmail(email: String, pinCode: String): Observable<User>

    fun verifyByMobile(mobile: String, pinCode: String): Observable<User>

    fun resetPassword(email: String, pinCode: String, password: String): Observable<User>

    fun signUp(email: String, password: String): Observable<User>

    fun getUser(): Observable<User>
}