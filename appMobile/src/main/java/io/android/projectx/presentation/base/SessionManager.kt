package io.android.projectx.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.android.projectx.domain.features.usermanagement.interactor.GetUser
import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.presentation.base.mapper.UserViewMapper
import io.android.projectx.presentation.base.model.UserView
import io.android.projectx.presentation.base.state.Resource
import io.android.projectx.presentation.base.state.ResourceState
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject


class SessionManager @Inject constructor(
    private val getUser: GetUser?,
    private val mapper: UserViewMapper
) {

    // data
    private val cachedUser: MediatorLiveData<Resource<UserView>> = MediatorLiveData()

    val authUser: LiveData<Resource<UserView>>
        get() = cachedUser

    fun getUser(source: LiveData<Resource<UserView>>) {
        cachedUser.value = Resource(ResourceState.LOADING, null, null)
        cachedUser.addSource(source) { userAuthResource: Resource<UserView> ->
            cachedUser.value = userAuthResource
            // todo - check this step
            cachedUser.removeSource(source)
        }
        getUser?.execute(GetUserSubscriber())
    }

    fun authenticate(
        source: LiveData<Resource<UserView>>,
        email: String,
        password: String
    ) {
        //Timber.d("authenticate: authenticate...")
        //cachedUser.value = Resource(ResourceState.LOADING, null, null)
        //fetchUser?.execute(FetchUserSubscriber())
    }

    fun logOut(user: UserView) {
        Timber.d("logOut: logging out...")
        cachedUser.value = Resource(ResourceState.SUCCESS, user, null)
    }

    inner class GetUserSubscriber : DisposableObserver<User>() {
        override fun onNext(user: User) =
            cachedUser.postValue(Resource(ResourceState.SUCCESS, mapper.mapToView(user), null))

        override fun onComplete() {}

        override fun onError(e: Throwable) =
            cachedUser.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
    }

}