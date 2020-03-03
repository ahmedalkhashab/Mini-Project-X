package io.android.projectx.remote.features.usermanagement.service

import io.android.projectx.remote.features.usermanagement.model.UserModel
import io.android.projectx.remote.features.usermanagement.model.request.EmailCredentialRequest
import io.android.projectx.remote.features.usermanagement.model.request.MobileCredentialRequest
import io.android.projectx.remote.features.usermanagement.model.request.ResetPasswordCredentialRequest
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface UserManagementService {

    @POST("profile/login")
    fun login(@Body request: EmailCredentialRequest): Single<UserModel>

    @POST("profile/login")
    fun login(@Body request: MobileCredentialRequest): Single<UserModel>

    @GET("profile/otp/mobile/verify")
    fun verifyByMobile(@QueryMap params: Map<String, String>): Single<UserModel>

    @GET("profile/otp/email/verify")
    fun verifyByEmail(@QueryMap params: Map<String, String>): Single<UserModel>

    @GET("profile/password/forget")
    fun forgetPassword(@QueryMap params: Map<String, String>): Completable

    @POST("profile/password/reset")
    fun resetPassword(@Body request: ResetPasswordCredentialRequest): Single<UserModel>

    @POST("profile/register")
    fun signUp(@Body request: EmailCredentialRequest): Single<UserModel>

    @POST("profile/register")
    fun signUp(@Body request: MobileCredentialRequest): Single<UserModel>

    @GET("profile/logout")
    fun logout(@QueryMap params: Map<String, String>): Completable

    @GET("profile")
    fun getUser(): Single<UserModel>

}