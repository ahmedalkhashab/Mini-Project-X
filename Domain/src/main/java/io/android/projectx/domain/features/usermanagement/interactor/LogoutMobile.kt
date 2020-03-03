package io.android.projectx.domain.features.usermanagement.interactor

import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.domain.base.interactor.CompletableUseCase
import io.android.projectx.domain.features.usermanagement.repository.UserManagementRepository
import io.reactivex.Completable
import javax.inject.Inject

open class LogoutMobile @Inject constructor(
    private val userManagementRepository: UserManagementRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<LogoutMobile.Params?>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return userManagementRepository.logout(params.countryCode, params.mobileNumber)
    }

    data class Params constructor(val countryCode: String, val mobileNumber: String) {
        companion object {
            fun forLogoutMobile(countryCode: String, mobileNumber: String): Params {
                return Params(countryCode, mobileNumber)
            }
        }
    }

}