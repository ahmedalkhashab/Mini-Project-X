package io.android.projectx.presentation.base.state

class Resource<out T>(val status: Status, val data: T?, val throwable: Throwable?, val message: String?) {

    sealed class Status {

        object ERROR : Status()
        object LOADING : Status()
        object SUCCESS : Status()

        sealed class AuthStatus {
            object AUTHENTICATED : Status()
            object ANONYMOUS : Status()
        }
    }

    companion object {
        fun <T> success(data: T?): Resource<T?> =
            Resource(Status.SUCCESS, data, null, null)

        fun <T> error(data: T? = null, ex: Throwable?, message: String? = null): Resource<T?> =
            Resource(Status.ERROR, data, ex, message)

        fun <T> loading(data: T? = null): Resource<T?> =
            Resource(Status.LOADING, data, null, null)
    }

}