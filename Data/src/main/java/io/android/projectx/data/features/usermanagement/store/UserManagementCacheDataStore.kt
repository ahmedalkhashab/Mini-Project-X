package io.android.projectx.data.features.usermanagement.store

import io.android.projectx.androidextensions.security.decrypt
import io.android.projectx.androidextensions.security.encrypt
import io.android.projectx.cache.AppDatabase
import io.android.projectx.cache.features.config.model.Config
import io.android.projectx.data.features.usermanagement.model.UserEntity
import io.android.projectx.data.features.usermanagement.repository.UserManagementCache
import io.android.projectx.extensions.fromJson
import io.android.projectx.extensions.toJson
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

open class UserManagementCacheDataStore @Inject constructor(
    private val appDatabase: AppDatabase
) : UserManagementCache {

    companion object {
        const val KEY_USER = "user"
    }

    override fun clearUser() {
        //application.putPreference(KEY_USER, "")
        appDatabase.configDao().deleteConfigItem(KEY_USER)
    }

    override fun saveUser(userEntity: UserEntity, lastCache: Long) {
        //application.putPreference(KEY_USER, userEntity)
        appDatabase.configDao().insertConfig(Config(KEY_USER, encrypt(userEntity.toJson()), lastCache))
    }

    override fun areUserCached(): Single<Boolean> {
        //val user: UserEntity? = application.getPreference(KEY_USER)
        //return Single.just(user != null)
        return appDatabase.configDao().getConfig(KEY_USER)
            .onErrorReturn { Config(KEY_USER, "", lastCacheTime = 0) }
            .map { it.value.isNotBlank() }
    }

    override fun persistToken(key: String, value: String) {
        //application.putPreference(key, value)
        appDatabase.configDao().insertConfig(Config(key, encrypt(value), System.currentTimeMillis()))
    }

    override fun clearToken(key: String) {
        //application.putPreference(key, "")
        appDatabase.configDao().deleteConfigItem(key)
    }

    override fun getToken(key: String): Single<String> {
        /*val token = application.getPreference(key) ?: ""
        return Single.just(token)*/
        return appDatabase.configDao().getConfig(key)
            .onErrorReturn { Config(key, "", lastCacheTime = 0) }
            .map { decrypt(it.value) }
    }

    override fun forceLogout(): Completable {
        clearUser()
        return Completable.complete()
    }

    override fun logout(email: String): Completable {
        clearUser()
        return Completable.complete()
    }

    override fun logout(countryCode: String, mobileNumber: String): Completable {
        clearUser()
        return Completable.complete()
    }

    override fun getUser(): Observable<UserEntity> {
        /*val user: UserEntity? = application.getPreference(KEY_USER)
        return Observable.just(user)*/
        return appDatabase.configDao().getConfig(KEY_USER)
            .toObservable()
            .map { fromJson<UserEntity>(decrypt(it.value)) }
    }

}