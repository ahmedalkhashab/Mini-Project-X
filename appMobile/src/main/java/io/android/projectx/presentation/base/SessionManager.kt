package io.android.projectx.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.android.gms.tasks.OnCompleteListener
import io.android.projectx.domain.features.usermanagement.interactor.GetUser
import io.android.projectx.domain.features.usermanagement.interactor.LoginMobile
import io.android.projectx.domain.features.usermanagement.interactor.LogoutMobile
import io.android.projectx.domain.features.usermanagement.interactor.UpdateTokenCM
import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.presentation.base.mapper.UserViewMapper
import io.android.projectx.presentation.base.model.UserStatusView
import io.android.projectx.presentation.base.model.UserView
import io.android.projectx.presentation.base.state.AuthResource
import io.android.projectx.presentation.features.cloudmessaging.CloudMessagingService
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val getUser: GetUser?,
    private val login: LoginMobile?,
    private val logout: LogoutMobile?,
    private val updateTokenCM: UpdateTokenCM?,
    private val mapper: UserViewMapper
) {

    private val _user: MediatorLiveData<AuthResource<UserView?>> = MediatorLiveData()
    val cachedUser: LiveData<AuthResource<UserView?>> = _user

    fun login(countryCode: String, mobile: String, password: String) {
        _user.postValue(AuthResource.loading())
        login?.execute(
            LoginSubscriber(),
            LoginMobile.Params.forLogin(countryCode, mobile, password)
        )
    }

    fun logOut() {
        _user.postValue(AuthResource.loading())
        logout?.execute(LogoutSubscriber())
    }

    fun getUser() {
        _user.postValue(AuthResource.loading())
        getUser?.execute(GetUserSubscriber())
    }

    /**
     * Persist token on third-party servers using your Retrofit APIs client.
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     */
    private fun sendTokenCloudMessagingToServer(data: UserView) {
        // make a own server request here using your http client
        CloudMessagingService.getToken(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.w(task.exception, "getInstanceId failed")
                _user.postValue(AuthResource.error(ex = task.exception))
                return@OnCompleteListener
            }
            // Get new Instance ID token
            val token = task.result?.token
            Timber.i("Current token: $token")
            token?.let {
                updateTokenCM?.execute(
                    UpdateTokenCMSubscriber(data),
                    UpdateTokenCM.Params.forUpdateTokenCM(it)
                )
            }
        })
    }

    inner class LoginSubscriber : DisposableObserver<User>() {
        override fun onComplete() {}

        override fun onError(e: Throwable) = _user.postValue(AuthResource.error(ex = e))

        override fun onNext(user: User) {
            val data = mapper.mapToView(user)
            when (data.userStatus) {
                UserStatusView.Anonymous -> _user.postValue(AuthResource.anonymous(data, null))
                UserStatusView.Registered -> {
                    sendTokenCloudMessagingToServer(data)
                    //_user.postValue(AuthResource.authenticated(data))
                }
            }
        }
    }

    inner class GetUserSubscriber : DisposableObserver<User>() {
        override fun onComplete() {}

        override fun onError(e: Throwable) = _user.postValue(AuthResource.error(ex = e))

        override fun onNext(user: User) {
            val data = mapper.mapToView(user)
            when (data.userStatus) {
                UserStatusView.Anonymous -> _user.postValue(AuthResource.anonymous(data, null))
                UserStatusView.Registered -> _user.postValue(AuthResource.authenticated(data))
            }
        }
    }

    inner class LogoutSubscriber : DisposableCompletableObserver() {
        override fun onComplete() = _user.postValue(AuthResource.anonymous(null, null))

        override fun onError(e: Throwable) = _user.postValue(AuthResource.error(ex = e))
    }

    inner class UpdateTokenCMSubscriber(private val data: UserView) : DisposableSingleObserver<Boolean>() {

        override fun onSuccess(t: Boolean) = _user.postValue(AuthResource.authenticated(data))

        override fun onError(e: Throwable) = _user.postValue(AuthResource.error(ex = e))
    }

}