package io.android.projectx.data.features.usermanagement

import io.android.projectx.data.features.usermanagement.mapper.UserMapper
import io.android.projectx.data.features.usermanagement.repository.UserManagementCache
import io.android.projectx.data.features.usermanagement.store.UserManagementDataStoreFactory
import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.domain.features.usermanagement.model.UserStatus
import io.android.projectx.domain.features.usermanagement.repository.UserManagementRepository
import io.android.projectx.remote.base.interceptor.RequestHeaders
import io.android.projectx.remote.features.usermanagement.mapper.UserModelMapper
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserManagementDataRepository @Inject constructor(
    private val mapper: UserMapper,
    private val cache: UserManagementCache,
    private val factory: UserManagementDataStoreFactory,
    private val mapperModel: UserModelMapper
) : UserManagementRepository {

    private val userObservable: Observable<User> = Observable.create { emitter ->
        factory.getCacheDataStore().areUserCached().toObservable()
            .map { isCached ->
                if (isCached) {
                    factory.getCacheDataStore().getUser()
                        .map { entity ->
                            val user = mapper.mapFromEntity(entity)
                            emitter.onNext(user)
                        }
                } else {
                    val anonymousUser = User(-1, "Anonymous User", "", "", UserStatus.Anonymous)
                    factory.getCacheDataStore()
                        .saveUser(mapper.mapToEntity(anonymousUser), System.currentTimeMillis())
                    emitter.onNext(anonymousUser)
                }
            }.share()
    }

    override fun login(email: String, password: String): Observable<User> {
        return factory.getRemoteDataStore().login(email, password)
            .map {
                factory.getCacheDataStore().saveUser(it, System.currentTimeMillis())
                mapper.mapFromEntity(it)
            }
    }

    override fun login(
        countryCode: String,
        mobileNumber: String,
        password: String
    ): Observable<User> {
        return factory.getRemoteDataStore().login(countryCode, mobileNumber, password)
            .map { response ->
                val entity = mapperModel.mapFromModel(response.user)
                cache.saveUser(entity, System.currentTimeMillis())
                cache.persistToken(
                    RequestHeaders.AUTHORIZATION_SHORT_TERM_TOKEN,
                    response.accessToken
                )
                mapper.mapFromEntity(entity)
            }
    }

    override fun verifyByMobile(
        countryCode: String,
        mobileNumber: String,
        pinCode: String
    ): Observable<User> {
        return factory.getRemoteDataStore().verifyByMobile(countryCode, mobileNumber, pinCode)
            .map {
                factory.getCacheDataStore().saveUser(it, System.currentTimeMillis())
                mapper.mapFromEntity(it)
            }
    }

    override fun verifyByEmail(email: String, pinCode: String): Observable<User> {
        return factory.getRemoteDataStore().verifyByEmail(email, pinCode)
            .map {
                factory.getCacheDataStore().saveUser(it, System.currentTimeMillis())
                mapper.mapFromEntity(it)
            }
    }

    override fun forgetPassword(email: String): Completable {
        return factory.getRemoteDataStore().forgetPassword(email)
    }

    override fun resetPassword(email: String, pinCode: String, password: String): Observable<User> {
        return factory.getRemoteDataStore().resetPassword(email, pinCode, password)
            .map {
                factory.getCacheDataStore().saveUser(it, System.currentTimeMillis())
                mapper.mapFromEntity(it)
            }
    }

    override fun signUp(email: String, password: String): Observable<User> {
        return factory.getRemoteDataStore().signUp(email, password)
            .map {
                factory.getCacheDataStore().saveUser(it, System.currentTimeMillis())
                mapper.mapFromEntity(it)
            }
    }

    override fun signUp(
        countryCode: String,
        mobileNumber: String,
        password: String
    ): Observable<User> {
        return factory.getRemoteDataStore().signUp(countryCode, mobileNumber, password)
            .map {
                factory.getCacheDataStore().saveUser(it, System.currentTimeMillis())
                mapper.mapFromEntity(it)
            }
    }

    override fun forceLogout(): Completable {
        return factory.getCacheDataStore().forceLogout()
    }

    override fun logout(email: String): Completable {
        return factory.getRemoteDataStore().logout(email)
            .doOnComplete { factory.getCacheDataStore().logout(email) }
    }

    override fun logout(countryCode: String, mobileNumber: String): Completable {
        return factory.getRemoteDataStore().logout(countryCode, mobileNumber)
            .doOnComplete { factory.getCacheDataStore().logout(countryCode, mobileNumber) }
    }

    override fun getUser(): Observable<User> {
        return userObservable
    }

    override fun fetchUser(): Observable<User> {
        return factory.getRemoteDataStore().fetchUser()
            .map { entity ->
                factory.getCacheDataStore().saveUser(entity, System.currentTimeMillis())
                mapper.mapFromEntity(entity)
            }
    }

}