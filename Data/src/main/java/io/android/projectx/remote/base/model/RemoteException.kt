package io.android.projectx.remote.base.model

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException

/**
 *
 * @property url The request URL which produced the error.
 * @property response Response object containing status code, headers, body, etc.
 * @property kind The event kind which triggered this error.
 * @property retrofit The Retrofit this request was executed on.
 */
class RemoteException(
    message: String?,
    val url: String?,
    val response: Response<*>?,
    val code: Int?,
    val kind: Kind,
    exception: Throwable?,
    val retrofit: Retrofit?) : RuntimeException(message, exception) {

    /** The data returned from the server in the response body*/
    var errorData: ServerError? = null

    companion object {
        fun httpError(exception: HttpException, retrofit: Retrofit): RemoteException {
            val response = exception.response() ?: return unexpectedError(exception)
            val url = response.raw().request.url.toString()
            val error = RemoteException(
                response.message(),
                url,
                response,
                response.code(),
                Kind.HTTP,
                null,
                retrofit
            )
            error.deserializeServerError()
            return error
        }

        fun httpErrorWithObject(exception: HttpException, retrofit: Retrofit): RemoteException {
            val response = exception.response() ?: return unexpectedError(exception)
            val url = response.raw().request.url.toString()
            val error = RemoteException(
                response.message(),
                url,
                response,
                response.code(),
                Kind.HTTP422WithDATA,
                null,
                retrofit
            )
            error.deserializeServerError()
            return error
        }

        fun authenticationError(exception: HttpException, retrofit: Retrofit): RemoteException {
            val response = exception.response() ?: return unexpectedError(exception)
            val url = response.raw().request.url.toString()
            val error = RemoteException(
                response.message(),
                url,
                response,
                response.code(),
                Kind.AUTHENTICATION,
                null,
                retrofit
            )
            error.deserializeServerError()
            return error
        }

        fun networkError(exception: IOException): RemoteException {
            return RemoteException(
                exception.message,
                null,
                null,
                null,
                Kind.NETWORK,
                exception,
                null
            )
        }

        fun timeoutError(exception: IOException): RemoteException {
            return RemoteException(
                exception.message,
                null,
                null,
                null,
                Kind.TIMEOUT,
                exception,
                null
            )
        }

        fun unexpectedError(exception: Throwable): RemoteException {
            return RemoteException(
                exception.message,
                null,
                null,
                null,
                Kind.UNEXPECTED,
                exception,
                null
            )
        }
    }

    private fun deserializeServerError() {
        if (response?.errorBody() != null) {
            try {
                errorData = getErrorBodyAs(ServerError::class.java)
            } catch (e: IOException) {
                Timber.tag("deserializeServerError").e(e)
            }
        }
    }

    /**
     * HTTP response body converted to specified `type`. `null` if there is no
     * response.
     * @throws IOException if unable to convert the body to the specified `type`.
     */
    @Throws(IOException::class)
    private fun <T> getErrorBodyAs(type: Class<T>): T? {
        if (response?.errorBody() == null || retrofit == null) return null
        val converter: Converter<ResponseBody, T> =
            retrofit.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
        return converter.convert(response.errorBody()
            ?: throw IllegalArgumentException("Null Response"))
    }

    sealed class Kind {
        /** An [IOException] occurred while communicating to the server.  */
        object NETWORK : Kind()
        /** An [IOException].  */
        object TIMEOUT : Kind()
        /** A non-200 HTTP status code was received from the server.  */
        object HTTP : Kind()
        object HTTP422WithDATA : Kind()
        object AUTHENTICATION : Kind()
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        object UNEXPECTED : Kind()
    }

}