package io.android.projectx.presentation.base.state

import io.android.projectx.presentation.base.state.Resource.Status.AuthStatus

class AuthResource<out T>(
    val status: Resource.Status,
    val data: T?,
    val throwable: Throwable?,
    val message: String?
) {

    companion object {
        fun <T> authenticated(data: T): AuthResource<T> =
            AuthResource(AuthStatus.AUTHENTICATED, data, null, null)

        fun <T> error(data: T? = null, ex: Throwable?): AuthResource<T?> =
            AuthResource(Resource.Status.ERROR, data, ex, null)

        fun <T> loading(data: T? = null): AuthResource<T?> =
            AuthResource(Resource.Status.LOADING, data, null, null)

        fun <T> anonymous(data: T? = null, ex: Throwable?): AuthResource<T> =
            AuthResource(AuthStatus.ANONYMOUS, data, ex, null)
    }

}