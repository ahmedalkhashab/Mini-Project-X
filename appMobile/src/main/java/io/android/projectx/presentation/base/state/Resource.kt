package io.android.projectx.presentation.base.state

class Resource<out T>(val status: ResourceState, val data: T?, val message: String?) {

    enum class ResourceState {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <T> success(data: T): Resource<T?> =
            Resource(ResourceState.SUCCESS, data, null)

        fun <T> error(message: String, data: T? = null): Resource<T?> =
            Resource(ResourceState.ERROR, data, message)

        fun <T> loading(data: T? = null): Resource<T?> =
            Resource(ResourceState.LOADING, data, null)
    }

}