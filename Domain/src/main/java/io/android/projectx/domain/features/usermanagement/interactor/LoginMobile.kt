package io.android.projectx.domain.features.usermanagement.interactor

import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.base.interactor.ObservableUseCase
import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.domain.features.usermanagement.repository.UserManagementRepository
import io.reactivex.Observable
import javax.inject.Inject

open class LoginMobile @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<User, LoginMobile.Params?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<User> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return userManagementRepository.login(
            params.countryCode,
            params.mobileNumber,
            params.password
        )
    }

    data class Params constructor(
        val countryCode: String,
        val mobileNumber: String,
        val password: String
    ) {
        companion object {
            fun forLogin(countryCode: String, mobileNumber: String, password: String): Params {
                return Params(countryCode, mobileNumber, password)
            }
        }
    }

}