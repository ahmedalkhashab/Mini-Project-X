package io.android.projectx.remote.base.interceptor

import io.android.projectx.data.features.usermanagement.repository.UserManagementCache
import javax.inject.Inject

class RequestHeaders @Inject constructor(
    private val userManagementCache: UserManagementCache
) {

    companion object {
        const val AUTHORIZATION_SHORT_TERM_TOKEN = "Authorization"
        const val AUTHORIZATION_LONG_TERM_TOKEN = "refresh-token"
    }

    //ShortTerm
    fun getShortTermTokenName() = AUTHORIZATION_SHORT_TERM_TOKEN

    fun getShortTermTokenValue(): String =
        userManagementCache.getToken(getShortTermTokenName())
            .blockingGet()

    fun hasShortTermToken() = getShortTermTokenValue().isNotBlank()

    fun setShortTermTokenValue(value: String) {
        userManagementCache.persistToken(getShortTermTokenName(), value)
    }

    // LongTerm
    fun getLongTermTokenName() = AUTHORIZATION_LONG_TERM_TOKEN

    fun getLongTermTokenValue(): String =
        userManagementCache.getToken(getLongTermTokenName())
            .blockingGet()

    fun hasLongTermToken() = getLongTermTokenValue().isNotBlank()

    fun setLongTermTokenValue(value: String) {
        userManagementCache.persistToken(getLongTermTokenName(), value)
    }
}