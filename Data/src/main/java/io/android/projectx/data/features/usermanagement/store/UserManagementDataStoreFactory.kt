package io.android.projectx.data.features.usermanagement.store

import io.android.projectx.data.features.usermanagement.repository.UserManagementCache
import io.android.projectx.data.features.usermanagement.repository.UserManagementDataStore
import io.android.projectx.data.features.usermanagement.repository.UserManagementRemote
import javax.inject.Inject

open class UserManagementDataStoreFactory @Inject constructor(
    private val userManagementCacheDataStore: UserManagementCacheDataStore,
    private val userManagementRemoteDataStore: UserManagementRemoteDataStore
) {

    open fun getDataStore(userCached: Boolean, cacheExpired: Boolean): UserManagementDataStore {
        return if (userCached && !cacheExpired) userManagementCacheDataStore
        else userManagementRemoteDataStore
    }

    open fun getCacheDataStore(): UserManagementCache {
        return userManagementCacheDataStore
    }

    open fun getRemoteDataStore(): UserManagementRemote {
        return userManagementRemoteDataStore
    }

}