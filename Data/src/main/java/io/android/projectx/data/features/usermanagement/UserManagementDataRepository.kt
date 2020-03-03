package io.android.projectx.data.features.usermanagement

import io.android.projectx.data.features.usermanagement.mapper.UserMapper
import io.android.projectx.data.features.usermanagement.repository.UserManagementCache
import io.android.projectx.data.features.usermanagement.store.UserManagementDataStoreFactory
import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.domain.features.usermanagement.repository.UserManagementRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserManagementDataRepository @Inject constructor(
    private val mapper: UserMapper,
    private val cache: UserManagementCache,
    private val factory: UserManagementDataStoreFactory
) : UserManagementRepository {

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
            .map {
                factory.getCacheDataStore().saveUser(it, System.currentTimeMillis())
                mapper.mapFromEntity(it)
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

    override fun logout(email: String): Completable {
        return factory.getRemoteDataStore().logout(email)
            .doOnComplete { factory.getCacheDataStore().logout(email) }
    }

    override fun logout(countryCode: String, mobileNumber: String): Completable {
        return factory.getRemoteDataStore().logout(countryCode, mobileNumber)
            .doOnComplete { factory.getCacheDataStore().logout(countryCode, mobileNumber) }
    }

    override fun getUser(): Observable<User> {
        return factory.getCacheDataStore().areUserCached().toObservable()
            .flatMap {
                if (it) factory.getCacheDataStore().getUser()
                    .map { entity -> mapper.mapFromEntity(entity) }
                else factory.getRemoteDataStore().getUser()
                    .map { entity ->
                        factory.getCacheDataStore().saveUser(entity, System.currentTimeMillis())
                        mapper.mapFromEntity(entity)
                    }
            }
    }

}