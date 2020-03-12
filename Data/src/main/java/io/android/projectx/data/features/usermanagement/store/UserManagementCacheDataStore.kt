package io.android.projectx.data.features.usermanagement.store

import io.android.projectx.cache.AppDatabase
import io.android.projectx.extensions.fromJson
import io.android.projectx.extensions.toJson
import io.android.projectx.cache.features.config.model.Config
import io.android.projectx.data.features.usermanagement.model.UserEntity
import io.android.projectx.data.features.usermanagement.repository.UserManagementCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

open class UserManagementCacheDataStore @Inject constructor(
    private val appDatabase: AppDatabase
) : UserManagementCache {

    companion object {
        const val KEY_USER_MANAGEMENT = "key_user_management"
    }

    override fun clearUser(): Completable {
        return appDatabase.configDao().deleteConfigItem(KEY_USER_MANAGEMENT)
    }

    override fun saveUser(userEntity: UserEntity, lastCache: Long): Completable {
        return appDatabase.configDao()
            .insertConfig(
                Config(KEY_USER_MANAGEMENT, value = userEntity.toJson(), lastCacheTime = lastCache)
            )
    }

    override fun areUserCached(): Single<Boolean> {
        return appDatabase.configDao().getConfig(KEY_USER_MANAGEMENT)
            .onErrorReturn {
                Config(KEY_USER_MANAGEMENT, "", lastCacheTime = 0)
            }
            .map { it.value.isNotBlank() }
    }

    override fun persistToken(key: String, value: String): Completable {
        return appDatabase.configDao()
            .insertConfig(Config(key, value = value, lastCacheTime = System.currentTimeMillis()))
    }

    override fun clearToken(key: String): Completable {
        return appDatabase.configDao().deleteConfigItem(key)
    }

    override fun getToken(key: String): Single<String> {
        return appDatabase.configDao().getConfig(key)
            .onErrorReturn {
                Config(key, "", lastCacheTime = 0)
            }
            .map { it.value }
    }

    override fun forceLogout(): Completable {
        return clearUser()
    }

    override fun logout(email: String): Completable {
        return clearUser()
    }

    override fun logout(countryCode: String, mobileNumber: String): Completable {
        return clearUser()
    }

    override fun getUser(): Observable<UserEntity> {
        return appDatabase.configDao().getConfig(KEY_USER_MANAGEMENT).toObservable()
            .map { fromJson<UserEntity>(it.value) }
    }

}