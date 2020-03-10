package io.android.projectx.presentation.base.state

class AuthResource<out T>(val status: AuthStatus, val data: T?, val message: String?) {

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }

    companion object {
        fun <T> authenticated(data: T): AuthResource<T> =
            AuthResource(AuthStatus.AUTHENTICATED, data, null)

        fun <T> error(message: String, data: T? = null): AuthResource<T?> =
            AuthResource(AuthStatus.ERROR, data, message)

        fun <T> loading(data: T? = null): AuthResource<T?> =
            AuthResource(AuthStatus.LOADING, data, null)

        fun <T> anonymous(data: T): AuthResource<T> =
            AuthResource(AuthStatus.NOT_AUTHENTICATED, data, null)
    }

}