package io.android.projectx.remote.base.interceptor

import android.util.Log
import io.android.projectx.data.features.usermanagement.UserManagementDataRepository
import io.android.projectx.data.features.usermanagement.store.UserManagementRemoteDataStore
import io.android.projectx.domain.features.usermanagement.model.User
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException
import javax.inject.Inject

class Authenticator @Inject constructor(
    private val requestHeaders: RequestHeaders,
    private val userManagementRepository: dagger.Lazy<UserManagementDataRepository>,
    private val userManagementRemote: dagger.Lazy<UserManagementRemoteDataStore>
) : Authenticator {

    companion object {
        const val TAG = "Authenticator"
    }

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
                            .build()
                    }
                } else forceLogout(response.request)
            } else forceLogout(response.request)
        }
        return null
    }

    private fun forceLogout(request: Request) {
        val remote = userManagementRemote.get()
        if (remote.isAuthenticatorURL(request.url.toUrl().path)) return
        else {
            val repository = userManagementRepository.get()
            val disposables = CompositeDisposable()
            val disposable = repository.forceLogout()
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        Log.d(TAG, "Force Logout: onComplete")
                        emitAnonymousUser(disposables)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "Force Logout: onError")
                        disposables.dispose()
                    }

                })
            disposables.add(disposable)
        }
    }

    /**
     * get anonymous user after force logout
     * to make repository emit the changed user to all subscribers
     */
    private fun emitAnonymousUser(disposables: CompositeDisposable) {
        val repository = userManagementRepository.get()
        val disposable = repository.getUser()
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableObserver<User>() {

                override fun onComplete() {
                    Log.d(TAG, "Emit Anonymous User: onComplete")
                    disposables.dispose()
                }

                override fun onNext(anonymousUser: User) {
                    Log.d(TAG, "Emit Anonymous User: onNext")
                    Log.d(TAG, anonymousUser.userStatus.toString())
                    disposables.dispose()
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "Emit Anonymous User: onError")
                    disposables.dispose()
                }
            })
        disposables.add(disposable)
    }

}