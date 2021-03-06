package io.android.projectx.domain.features.usermanagement.interactor

import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.base.interactor.ObservableUseCase
import io.android.projectx.domain.features.usermanagement.model.User
import io.android.projectx.domain.features.usermanagement.repository.UserManagementRepository
import io.reactivex.Observable
import javax.inject.Inject

open class VerifyByMobile @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<User, VerifyByMobile.Params?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<User> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return userManagementRepository.verifyByMobile(
            params.countryCode,
            params.mobileNumber,
            params.pinCode
        )
    }

    data class Params constructor(
        val countryCode: String,
        val mobileNumber: String,
        val pinCode: String
    ) {
        companion object {
            fun forVerifyByMobile(
                countryCode: String,
                mobileNumber: String,
                pinCode: String
            ): Params {
                return Params(countryCode, mobileNumber, pinCode)
            }
        }
    }

}