package io.android.projectx.data.features.usermanagement.store

import io.android.projectx.data.features.usermanagement.model.UserEntity
import io.android.projectx.data.features.usermanagement.repository.UserManagementRemote
import io.android.projectx.remote.features.usermanagement.mapper.UserModelMapper
import io.android.projectx.remote.features.usermanagement.model.request.EmailCredentialRequest
import io.android.projectx.remote.features.usermanagement.model.request.MobileCredentialRequest
import io.android.projectx.remote.features.usermanagement.model.request.MobileNumber
import io.android.projectx.remote.features.usermanagement.model.request.ResetPasswordCredentialRequest
import io.android.projectx.remote.features.usermanagement.service.AuthenticatorURL
import io.android.projectx.remote.features.usermanagement.service.UserManagementService
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.Call
import java.util.*
import javax.inject.Inject

open class UserManagementRemoteDataStore @Inject constructor(
    private val service: UserManagementService,
    private val mapper: UserModelMapper
) : UserManagementRemote {

    companion object {
        private const val EMAIL = "email"
        private const val COUNTRY_CODE = "country_code"
        private const val MOBILE_NUMBER = "mobile_number"
        private const val PIN_CODE = "pin_code"
    }

    override fun login(email: String, password: String): Observable<UserEntity> {
        return service.login(EmailCredentialRequest(email, password))
            .toObservable()
            .map { mapper.mapFromModel(it) }
    }

    override fun login(
        countryCode: String,
        mobileNumber: String,
        password: String
    ): Observable<UserEntity> {
        val mobile = MobileNumber(countryCode, mobileNumber)
        return service.login(MobileCredentialRequest(mobile, password))
            .toObservable()
            .map { mapper.mapFromModel(it) }
    }

    override fun verifyByMobile(
        countryCode: String,
        mobileNumber: String,
        pinCode: String
    ): Observable<UserEntity> {
        val params = HashMap<String, String>()
        params[COUNTRY_CODE] = countryCode
        params[MOBILE_NUMBER] = mobileNumber
        params[PIN_CODE] = pinCode
        return service.verifyByMobile(params)
            .toObservable()
            .map { mapper.mapFromModel(it) }
    }

    override fun verifyByEmail(email: String, pinCode: String): Observable<UserEntity> {
        val params = HashMap<String, String>()
        params[EMAIL] = email
        params[PIN_CODE] = pinCode
        return service.verifyByEmail(params)
            .toObservable()
            .map { mapper.mapFromModel(it) }
    }

    override fun forgetPassword(email: String): Completable {
        val params = HashMap<String, String>()
        params[EMAIL] = email
        return service.forgetPassword(params)
    }

    override fun resetPassword(
        email: String,
        pinCode: String,
        password: String
    ): Observable<UserEntity> {
        return service.resetPassword(ResetPasswordCredentialRequest(email, pinCode, password))
            .toObservable()
            .map { mapper.mapFromModel(it) }
    }

    override fun signUp(email: String, password: String): Observable<UserEntity> {
        return service.signUp(EmailCredentialRequest(email, password))
            .toObservable()
            .map { mapper.mapFromModel(it) }
    }

    override fun signUp(
        countryCode: String,
        mobileNumber: String,
        password: String
    ): Observable<UserEntity> {
        val mobile = MobileNumber(countryCode, mobileNumber)
        return service.signUp(MobileCredentialRequest(mobile, password))
            .toObservable()
            .map { mapper.mapFromModel(it) }
    }

    override fun forceLogout(): Completable {
        throw UnsupportedOperationException("force Logout isn't supported here, because the user has no session at server")
    }

    override fun logout(email: String): Completable {
        val params = HashMap<String, String>()
        params[EMAIL] = email
        return service.logout(params)
    }

    override fun logout(countryCode: String, mobileNumber: String): Completable {
        val params = HashMap<String, String>()
        params[COUNTRY_CODE] = countryCode
        params[MOBILE_NUMBER] = mobileNumber
        return service.logout(params)
    }

    override fun refreshShortToken(tokenLongTerm: String): Call<String> {
        return service.refreshShortToken(tokenLongTerm)
    }

    override fun isAuthenticatorURL(responseWebServiceURL: String): Boolean {
        return responseWebServiceURL.contains(AuthenticatorURL.login)
                || responseWebServiceURL.contains(AuthenticatorURL.verifyByMobile)
                || responseWebServiceURL.contains(AuthenticatorURL.verifyByEmail)
    }

    override fun fetchUser(): Observable<UserEntity> {
        return service.fetchUser()
            .toObservable()
            .map { mapper.mapFromModel(it) }
    }

}