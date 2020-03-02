package io.android.projectx.domain.features.usermanagement.interactor

import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.base.interactor.ObservableUseCase
import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.domain.features.usermanagement.repository.UserManagementRepository
import io.reactivex.Observable
import javax.inject.Inject

open class ResetPassword @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<User, ResetPassword.Params?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<User> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return userManagementRepository.resetPassword(params.email, params.pinCode, params.password)
    }

    data class Params constructor(val email: String, val pinCode: String, val password: String) {
        companion object {
            fun forResetPassword(email: String, pinCode: String, password: String): Params {
                return Params(email, pinCode, password)
            }
        }
    }

}