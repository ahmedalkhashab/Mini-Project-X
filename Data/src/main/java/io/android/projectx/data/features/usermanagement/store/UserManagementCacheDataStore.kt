package io.android.projectx.data.features.usermanagement.store

import android.app.Application
import io.android.projectx.androidextensions.preferences.getPreference
import io.android.projectx.androidextensions.preferences.putPreference
import io.android.projectx.data.features.usermanagement.model.UserEntity
import io.android.projectx.data.features.usermanagement.repository.UserManagementCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

open class UserManagementCacheDataStore @Inject constructor(
    //private val appDatabase: AppDatabase
    private val application: Application
) : UserManagementCache {

    companion object {
        const val KEY_USER = "user"
    }

    override fun clearUser() {
        application.putPreference(KEY_USER, "")
        //appDatabase.configDao().deleteConfigItem(KEY_USER)
    }

    override fun saveUser(userEntity: UserEntity, lastCache: Long) {
        application.putPreference(KEY_USER, userEntity)
        //appDatabase.configDao().insertConfig(Config(KEY_USER, encrypt(userEntity.toJson()), lastCache))
    }

    override fun areUserCached(): Boolean {
        val user: UserEntity? = application.getPreference(KEY_USER)
        return user != null
        /*return appDatabase.configDao().getConfig(KEY_USER)
            .onErrorReturn { Config(KEY_USER, "", lastCacheTime = 0) }
            .map { it.value.isNotBlank() }*/
    }

    override fun persistToken(key: String, value: String) {
        application.putPreference(key, value)
        /*appDatabase.configDao().insertConfig(Config(key, encrypt(value), System.currentTimeMillis()))*/
    }

    override fun clearToken(key: String) {
        application.putPreference(key, "")
        //appDatabase.configDao().deleteConfigItem(key)
    }

    override fun getToken(key: String): Single<String> {
        val token = application.getPreference(key) ?: ""
        return Single.just(token)
        /*return appDatabase.configDao().getConfig(key)
            .onErrorReturn { Config(key, "", lastCacheTime = 0) }
            .map { decrypt(it.value) }*/
    }

    override fun forceLogout(): Completable {
        clearUser()
        return Completable.complete()
    }

    override fun logout(email: String): Completable {
        clearUser()
        return Completable.complete()
    }

    override fun logout(): Completable {
        clearUser()
        return Completable.complete()
    }

    override fun logout(countryCode: String, mobileNumber: String): Completable {
        clearUser()
        return Completable.complete()
    }

    override fun getUser(): UserEntity {
        val user: UserEntity? = application.getPreference(KEY_USER)
        return user!!
        /*return appDatabase.configDao().getConfig(KEY_USER)
            .toObservable()
            .map { fromJson<UserEntity>(decrypt(it.value)) }*/
    }

}