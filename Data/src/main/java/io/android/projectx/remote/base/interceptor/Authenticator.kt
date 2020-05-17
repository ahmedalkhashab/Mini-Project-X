package io.android.projectx.remote.base.interceptor

import io.android.projectx.data.features.usermanagement.store.UserManagementRemoteDataStore
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException
import javax.inject.Inject

class Authenticator @Inject constructor(
    private val requestHeaders: RequestHeaders,
    private val userManagementRemote: dagger.Lazy<UserManagementRemoteDataStore>
) : Authenticator {

    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == 401) {
            val tokenLongTerm = requestHeaders.getLongTermTokenValue()
            if (tokenLongTerm.isNotBlank()) {
                val remote = userManagementRemote.get()
                val refreshResponse = remote.refreshShortToken(tokenLongTerm).execute()
                if (refreshResponse.code() == 200) {
                    //read new JWT value from response body or interceptor depending upon your JWT availability logic
                    val newShortTermToken =
                        refreshResponse.headers()[requestHeaders.getShortTermTokenName()]
                    if (newShortTermToken != null) {
                        requestHeaders.setShortTermTokenValue(newShortTermToken)
                        return response.request.newBuilder()
                            .header(requestHeaders.getShortTermTokenName(), newShortTermToken)
                            .header(
                                requestHeaders.getAcceptLanguageKey(),
                                requestHeaders.getLanguageValue()
                            )
                            .build()
                    }
                }
            }
        }
        return null
    }

}