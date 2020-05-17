package io.android.projectx.remote.features.usermanagement.service

import io.android.projectx.remote.features.usermanagement.model.request.cloudmessaging.TokenCmRequestModel
import io.android.projectx.remote.features.usermanagement.model.UserModel
import io.android.projectx.remote.features.usermanagement.model.request.EmailCredentialRequest
import io.android.projectx.remote.features.usermanagement.model.request.MobileCredentialRequest
import io.android.projectx.remote.features.usermanagement.model.request.ResetPasswordCredentialRequest
import io.android.projectx.remote.features.usermanagement.model.response.CloudRegistrationWrapper
import io.android.projectx.remote.features.usermanagement.model.response.LoginWrapper
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

object AuthenticatorURL {
    const val login: String = "auth"
    const val logout: String = "auth"
    const val verifyByMobile: String = "profile/otp/mobile/verify"
    const val verifyByEmail: String = "profile/otp/email/verify"
}

interface UserManagementService {

    @POST(AuthenticatorURL.login)
    fun login(@Body request: EmailCredentialRequest): Single<UserModel>

    @POST(AuthenticatorURL.login)
    fun login(@Body request: MobileCredentialRequest): Single<LoginWrapper>

    @GET(AuthenticatorURL.verifyByMobile)
    fun verifyByMobile(@QueryMap params: Map<String, String>): Single<UserModel>

    @GET(AuthenticatorURL.verifyByEmail)
    fun verifyByEmail(@QueryMap params: Map<String, String>): Single<UserModel>

    @GET("profile/password/forget")
    fun forgetPassword(@QueryMap params: Map<String, String>): Completable

    @POST("profile/password/reset")
    fun resetPassword(@Body request: ResetPasswordCredentialRequest): Single<UserModel>

    @POST("profile/register")
    fun signUp(@Body request: EmailCredentialRequest): Single<UserModel>

    @POST("profile/register")
    fun signUp(@Body request: MobileCredentialRequest): Single<UserModel>

    @DELETE(AuthenticatorURL.logout)
    fun logout(): Completable

    @GET("profile/logout")
    fun logout(@QueryMap params: Map<String, String>): Completable

    @GET("profile")
    fun fetchUser(): Single<UserModel>

    @POST("profile/token/short-term")
    fun refreshShortToken(@Body tokenLongTerm: String): Call<String>

    @POST("device_tokens")
    fun updateDeviceToken(@Body body: TokenCmRequestModel): Single<CloudRegistrationWrapper>

}