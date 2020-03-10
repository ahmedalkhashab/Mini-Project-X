package io.android.projectx.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.android.projectx.domain.features.usermanagement.interactor.GetUser
import io.android.projectx.domain.features.usermanagement.interactor.LoginMobile
import io.android.projectx.domain.features.usermanagement.interactor.LogoutMobile
import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.presentation.base.mapper.UserViewMapper
import io.android.projectx.presentation.base.model.UserStatusView
import io.android.projectx.presentation.base.model.UserView
import io.android.projectx.presentation.base.state.AuthResource
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val getUser: GetUser?,
    private val login: LoginMobile?,
    private val logout: LogoutMobile?,
    private val mapper: UserViewMapper
) {

    private val _user: MediatorLiveData<AuthResource<UserView?>> = MediatorLiveData()
    val cachedUser: LiveData<AuthResource<UserView?>> = _user

    fun login(countryCode: String, mobile: String, password: String) {
        _user.value = AuthResource.loading()
        login?.execute(
            GetUserSubscriber(),
            LoginMobile.Params.forLogin(countryCode, mobile, password)
        )
    }

    fun logOut(countryCode: String, mobile: String) {
        _user.value = AuthResource.loading()
        logout?.execute(
            LogoutSubscriber(),
            LogoutMobile.Params.forLogoutMobile(countryCode, mobile)
        )
    }

    fun getUser() {
        _user.value = AuthResource.loading()
        getUser?.execute(GetUserSubscriber())
    }

    inner class GetUserSubscriber : DisposableObserver<User>() {
        override fun onComplete() {}

        override fun onError(e: Throwable) = _user.postValue(AuthResource.error(e.localizedMessage))

        override fun onNext(user: User) {
            val data = mapper.mapToView(user)
            when (data.userStatus) {
                UserStatusView.Anonymous -> _user.postValue(AuthResource.anonymous(data))
                UserStatusView.Registered -> _user.postValue(AuthResource.authenticated(data))
            }
        }
    }

    inner class LogoutSubscriber : DisposableCompletableObserver() {
        override fun onComplete() = getUser()

        override fun onError(e: Throwable) = _user.postValue(AuthResource.error(e.localizedMessage))
    }
}